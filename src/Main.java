import studetnsEducationModel.DevelopmentPlan;
import studetnsEducationModel.DevelopmentPlanBuilder;
import studetnsEducationModel.activity.Activity;
import studetnsEducationModel.activity.ActivityBuilder;
import studetnsEducationModel.activity.knowledgeSource.Institution;
import studetnsEducationModel.activity.knowledgeSource.Meetup;
import studetnsEducationModel.activity.knowledgeSource.SelfStudy;
import studetnsEducationModel.activity.knowledgeSource.Student;
import studetnsEducationModel.activity.schedule.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

public class Main {
    private static Set<DayOfWeek> holidays = new HashSet<DayOfWeek>() {{
        add(DayOfWeek.SUNDAY);
        add(DayOfWeek.SATURDAY);
    }};

    private static Set<Month> summer = new HashSet<Month>() {{
        add(Month.JUNE);
        add(Month.JULY);
        add(Month.AUGUST);
    }};

    public static void main(String[] args) {
        final List<Student> students = generateStudents(5);
        final LocalDate startDay = LocalDate.now();
        final Map<String, DevelopmentPlan> plans = generateDevelopmentPlans(students, startDay);

        int days = 100;
        simulate(startDay, days, students, plans);
    }

    private static void simulate(LocalDate startDay, int days, List<Student> students, Map<String, DevelopmentPlan> plans) {
        int i = 1;
        for (Student student : students) {
            System.out.printf("Student %d, ratio %.3f laptop %b\n", i, student.getLearningRatio(), student.hasLaptop());
            for (Map.Entry<String, DevelopmentPlan> entry : plans.entrySet()) {
                String name = entry.getKey();
                DevelopmentPlan plan = entry.getValue();

                LocalDate date = startDay;

                for (int j = 0; j < days; j++) {
                    plan.perform(student, date);
                    date = date.plusDays(1);
                }

                System.out.println("+ " + name);
                System.out.println("Knowledge points: " + student.getKnowledge());
                System.out.println("Practice points: " + student.getPractice());
                student.forgetAll();
            }
            System.out.println("-------------------");
            i++;
        }
    }

    private static List<Student> generateStudents(int amount) {
        List<Student> students = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < amount; i++)
            students.add(
                    new Student(
                            random.nextFloat(),
                            random.nextBoolean()
                    )
            );

        return students;
    }

    private static Map<String, DevelopmentPlan> generateDevelopmentPlans(List<Student> students, LocalDate startDay) {
        Institution university = new Institution(200, 10);
        Institution internship = new Institution(160, 100);

//      ===== Register some students
        Random random = new Random();
        for (Student student : students) {
            float chance = random.nextFloat();
            if (chance < 0.6f) {
                university.register(student);
                if (chance < 0.3f) internship.register(student);
            }
        }
//      =====

        Schedule workdays = ComplexSchedule.negate(new DayOfWeekSchedule(holidays));
        Schedule universitySchedule = ComplexSchedule.and(
                new PeriodSchedule(startDay, startDay.plusYears(5)),
                ComplexSchedule.negate(new MonthSchedule(summer)),
                workdays
        );

//      Activities

        Activity universityActivity = new ActivityBuilder()
                .schedule(universitySchedule)
                .source(new Institution())
                .build();

        Schedule internshipScheule = ComplexSchedule.and(
                new PeriodSchedule(startDay, startDay.plusMonths(3)),
                workdays
        );

        Activity gotoUniversity = new ActivityBuilder()
                .schedule(universitySchedule)
                .source(university)
                .build();

        Activity gotoMeetup = new ActivityBuilder()
                .schedule(new LastDayOfWeekInMonthSchedule(DayOfWeek.THURSDAY))
                .source(new Meetup())
                .build();

        Activity gotoInternship = new ActivityBuilder()
                .schedule(internshipScheule)
                .source(internship)
                .build();

        Activity selfstudy = new ActivityBuilder()
                .schedule(new EveryDaySchedule())
                .source(new SelfStudy())
                .build();

//      Plans

        DevelopmentPlan pacifist = new DevelopmentPlanBuilder()
                .add(gotoUniversity)
                .build();

        DevelopmentPlan selfTaught = new DevelopmentPlanBuilder()
                .add(gotoMeetup)
                .add(selfstudy)
                .add(gotoInternship)
                .build();

        DevelopmentPlan teachMeCompletely = new DevelopmentPlanBuilder()
                .add(gotoUniversity)
                .add(gotoMeetup)
                .build();

        DevelopmentPlan consciousStudent = new DevelopmentPlanBuilder()
                .add(gotoUniversity)
                .add(gotoMeetup)
                .add(selfstudy)
                .add(gotoInternship)
                .build();

        Map<String, DevelopmentPlan> plans = new HashMap();
        plans.put("Pacifist", pacifist);
        plans.put("Self-Taught", selfTaught);
        plans.put("Teach me completely", teachMeCompletely);
        plans.put("Conscious student", consciousStudent);
        return plans;
    }
}