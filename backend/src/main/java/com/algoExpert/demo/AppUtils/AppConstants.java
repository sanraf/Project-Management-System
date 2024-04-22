package com.algoExpert.demo.AppUtils;

public class AppConstants {

    public final static String ALREADY_A_MEMBER= "User ID %s is already a member";
    public final static String USER_NOT_FOUND = "User with ID %s user_id  not found";
    public final static String USERNAME_NOT_FOUND = "User with %s user_id  not found";
    public final static String USERNAME_ALREADY_EXIST = "Email %s already taken";
    public final static String PROJECT_NOT_FOUND = "Project with ID %s not found";
    public final static String TASK_NOT_FOUND = "Task with ID  %s not found";
    public final static String TOKEN_NOT_FOUND = "Token %s not found";
    public final static String ALREADY_ASSIGNED= "Member ID %s is already assigned this task";
    public final static String PASSWORD_MISMATCH = "Password not matching";
    public final static String USER_ROLE= "USER";
    public final static String MEMBER_ROLE= "MEMBER";
    public final static String OWNER_ROLE= "OWNER";
    public final static String TEMP_USER_EMAIL= "bimeg90742@eryod.com";
}
//@Scheduled(fixedRate = 20000)
//    private void todayDueDate() throws InvalidArgument {
//        LocalDate today = LocalDate.now();
//        List<Task> tasksDueToday = taskRepository.findTasksDueToday(today.toString());
//
//        for (Task task : tasksDueToday) {
//            List<Assignee> assignees = assigneesRepository.findByTaskId(task.getTask_id());
//            System.err.println(task.getTask_id()+" from today's date");
//            for (Assignee assignee : assignees) {
//                emailService.sendTaskReminderEmail(assignee.getUsername(),
//                        emailHtmlLayout.buildTaskDueDateReminderEmail(getUserFullName(assignee.getUsername()),
//                                task.getTitle(), task.getProjectName(), "Today", getTaskLink(task.getTask_id())), "Today");
//                User user = userRepository.findByEmail(assignee.getUsername()).orElseThrow();
//                notificationService.createNotification(user, "Task deadline: " + task.getTitle());
//                System.err.println("ID "+task.getTask_id()+" TODAY SAVED");
//            }
//        }
//    }
//
//    @Scheduled(fixedRate = 20000)
//    private void tomorrowDueDate() throws InvalidArgument {
//
//        LocalDate tomorrow = LocalDate.now().plusDays(1);
//        List<Task> taskDueTomorrow = taskRepository.findTasksDueTomorrow(tomorrow.toString());
//        for (Task task: taskDueTomorrow) {
//            List<Assignee> emails = assigneesRepository.findByTaskId(task.getTask_id());
//            System.err.println(task.getTask_id()+" from tomorrow's date");
//            for (Assignee assignee:emails){
//                emailService.sendTaskReminderEmail(TEMP_USER_EMAIL,
//                        emailHtmlLayout.buildTaskDueDateReminderEmail(getUserFullName(assignee.getUsername()),
//                                task.getTitle(),task.getProjectName(),"Tomorrow",getTaskLink(task.getTask_id())),"Tomorrow");
//                System.err.println(assignee.getUsername()+" Task Reminder: Tomorrow is the due date for Task # "+task.getProjectName());
//                //todo call notification entity and save the details in the database
//                User user = userRepository.findByEmail(assignee.getUsername()).orElseThrow();
//                notificationService.createNotification(user,"Task deadline Reminder: "+task.getTitle());
//                System.err.println("ID "+task.getTask_id()+" TOMORROW SAVED");
//            }
//        }
//
//    }
//
//    private String getUserFullName(String userName) throws InvalidArgument {
//        return userRepository.findByEmail(userName).orElseThrow(()->new  InvalidArgument(String.format(USERNAME_NOT_FOUND,userName))).getFullname();
//    }
//
//    private String getTaskLink(int taskId){
//        return taskUrl+taskId;
//    }