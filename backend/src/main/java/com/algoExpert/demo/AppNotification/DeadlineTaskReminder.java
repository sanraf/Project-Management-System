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
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import static com.algoExpert.demo.AppUtils.AppConstants.*;

/**
 *
 */
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


/**
 * Scheduled method to run reminders for tasks.
 * <p>
 * This method is scheduled to run at a fixed rate of  24 hours (5 am).
 * It invokes two methods {@link #todayDueDate()} and {@link #tomorrowDueDate()} to send notifications
 * to users based on the status and deadline of tasks,
 * then save task details to be displayed on app user's notification panel.
 * </p>
 *
 * <p>
 *  <strong>Warning:</strong> For this method to behave correctly, it should be scheduled to run once per day.
 * Running it more frequently may result in duplicate notifications being sent to users and to database.
 * </p>
 * <p>
 * The {@link #todayDueDate()} method sends notifications to users for tasks that have a deadline
 * set for the current day and have not been completed yet. It retrieves tasks from the database
 * and checks their end date and status. If the status is not <strong>'DONE'</strong>, it sends a reminder
 * notification to the user.
 * </p>
 *
 * <p>
 * The {@link #tomorrowDueDate()} method sends notifications to users for tasks that have a deadline
 * set for the next day. It retrieves tasks from the database and checks their end date. If the end date
 * matches the next day, it sends a reminder notification to the user.
 * </p>
 *
 * @throws InvalidArgument if there is an invalid argument passed to the methods.
 * @see #todayDueDate()
 * @see #tomorrowDueDate()
 * @author Santos Rafaelo
 */

//    @Scheduled(cron = "0 0 5 * * ?")
//    @PostConstruct  //use this for testing
    private void runReminder() throws InvalidArgument {
        todayDueDate();
        tomorrowDueDate();
    }

    public void todayDueDate() throws InvalidArgument {

        LocalDate today = LocalDate.now();

        List<Task> tasksDueToday = taskService.findTaskByDateAndStatus(today.toString(), STATUS);

        for (Task task : tasksDueToday) {
            List<Assignee> emails = assigneesRepository.findByTaskId(task.getTask_id());
            System.err.println("ID "+task.getTask_id()+" from today's date");
            for (Assignee assignee:emails){

                    //TODO change TEMP_USER_EMAIL to assignee.getUsername()
                    String body = emailHtmlLayout.taskDeadlineHtml(getUserFullName(assignee.getUsername()), task.getTitle(), task.getProjectName(), "Today", getTaskLink(task.getTask_id()));
                    appEmailBuilder.sendTaskReminderEmail(TEMP_USER_EMAIL,body,TODAY);

                    System.err.println(assignee.getUsername()+" Task Reminder: today is the due date for Task  "+task.getProjectName());
                    User user = userRepository.findByEmail(assignee.getUsername()).orElseThrow();

                    //save to database method
                    notificationService.createNotification(user, TODAY_MSG + task.getTitle());
                    System.err.println("ID "+task.getTask_id()+" TODAY SAVED");


            }
        }

        }



    public void tomorrowDueDate() throws InvalidArgument {

        LocalDate tomorrow = LocalDate.now().plusDays(1);
        List<Task> taskDueTomorrow = taskService.findTaskByDateAndStatus(tomorrow.toString(),STATUS);
        for (Task task: taskDueTomorrow) {
            List<Assignee> assignees = assigneesRepository.findByTaskId(task.getTask_id());
            System.err.println("ID "+task.getTask_id()+" from tomorrow's date");
            for (Assignee assignee:assignees){
                    //TODO change TEMP_USER_EMAIL to assignee.getUsername()
                    String body = emailHtmlLayout.taskDeadlineHtml(getUserFullName(assignee.getUsername()), task.getTitle(), task.getProjectName(), "Tomorrow", getTaskLink(task.getTask_id()));
                    appEmailBuilder.sendTaskReminderEmail(TEMP_USER_EMAIL, body,TOMORROW);

                    System.err.println(assignee.getUsername()+" Task Reminder: Tomorrow is the due date for Task # "+task.getProjectName());
                    User user = userRepository.findByEmail(assignee.getUsername()).orElseThrow();

                    notificationService.createNotification(user,TOMORROW_MSG+task.getTitle());
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

}
