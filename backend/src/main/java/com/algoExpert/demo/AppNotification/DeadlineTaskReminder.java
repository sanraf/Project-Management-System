package com.algoExpert.demo.AppNotification;

import com.algoExpert.demo.Entity.Assignee;
import com.algoExpert.demo.Entity.Task;
import com.algoExpert.demo.Entity.User;
import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import com.algoExpert.demo.Repository.AssigneesRepository;
import com.algoExpert.demo.Repository.Service.TaskService;
import com.algoExpert.demo.Repository.Service.UserNotificationService;
import com.algoExpert.demo.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.algoExpert.demo.AppUtils.AppConstants.TEMP_USER_EMAIL;
import static com.algoExpert.demo.AppUtils.AppConstants.USERNAME_NOT_FOUND;

@Service
@Slf4j
public class DeadlineTaskReminder {

    @Autowired
    private TaskService taskService;
    @Autowired
    private AssigneesRepository assigneesRepository;
    @Autowired
    private AppEmailBuilder appEmailBuilder;
    @Autowired
    private EmailHtmlLayout emailHtmlLayout;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserNotificationService notificationService;
    @Value("${task.reminder.url}")
    String taskUrl;
    public static final String STATUS = "COMPLETE";



//    @Scheduled(fixedRate = 10000)
    public void todayDueDate() throws InvalidArgument {

        LocalDate today = LocalDate.now();

        List<Task> tasksDueToday = taskService.findTaskByDateAndStatus(today.toString(), STATUS);

        for (Task task : tasksDueToday) {
            List<Assignee> emails = assigneesRepository.findByTaskId(task.getTask_id());
            System.err.println("ID "+task.getTask_id()+" from today's date");
            for (Assignee assignee:emails){

                appEmailBuilder.sendTaskReminderEmail(TEMP_USER_EMAIL,
                        emailHtmlLayout.buildTaskDueDateReminderEmail(getUserFullName(assignee.getUsername()),
                                task.getTitle(),task.getProjectName(),"Today",getTaskLink(task.getTask_id())),"Today");

                System.err.println(assignee.getUsername()+" Task Reminder: today is the due date for Task # "+task.getProjectName());
                User user = userRepository.findByEmail(assignee.getUsername()).orElseThrow();
                notificationService.createNotification(user, "Task deadline today: " + task.getTitle());
                System.err.println("ID "+task.getTask_id()+" TODAY SAVED");
            }
        }
            tasksDueToday.forEach(System.err::println);
        System.err.println(today);
        }


//    @Scheduled(fixedRate = 10000)
    public void tomorrowDueDate() throws InvalidArgument {

        LocalDate tomorrow = LocalDate.now().plusDays(1);
        List<Task> taskDueTomorrow = taskService.findTaskByDateAndStatus(tomorrow.toString(),STATUS);
        for (Task task: taskDueTomorrow) {
            List<Assignee> emails = assigneesRepository.findByTaskId(task.getTask_id());
            System.err.println("ID "+task.getTask_id()+" from tomorrow's date");
            for (Assignee assignee:emails){
                appEmailBuilder.sendTaskReminderEmail(TEMP_USER_EMAIL,
                        emailHtmlLayout.buildTaskDueDateReminderEmail(getUserFullName(assignee.getUsername()),
                                task.getTitle(),task.getProjectName(),"Tomorrow",getTaskLink(task.getTask_id())),"Tomorrow");

                System.err.println(assignee.getUsername()+" Task Reminder: Tomorrow is the due date for Task # "+task.getProjectName());
                User user = userRepository.findByEmail(assignee.getUsername()).orElseThrow();
                notificationService.createNotification(user,"Task deadline Reminder tomorrow: "+task.getTitle());
                System.err.println("ID "+task.getTask_id()+" TOMORROW SAVED");
            }
        }

    }

    private String getUserFullName(String userName) throws InvalidArgument {
        return userRepository.findByEmail(userName).orElseThrow(()->new  InvalidArgument(String.format(USERNAME_NOT_FOUND,userName))).getFullName();
    }

    private String getTaskLink(int taskId){
        return taskUrl+taskId;
    }

    //todo create to find the table the task belongs to

}
