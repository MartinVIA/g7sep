// package utils;

// import java.io.*;
// import java.util.ArrayList;
// import model.Date;
// import model.Task;
// import model.TasksList;


//   public class TasksXML {
//     private TasksList tasksList;
//   private ArrayList<Task> tasks;
  
//   pu    blic TasksXML() {
//         tasks = new ArrayList<>();
//         readTasks();
//       writeTasks();
//   }
  
//   pu    blic ArrayList<Task> getTasks() {
//       return tasks;
//   }
  
//   pu    blic void readTasks() { (FileInputStream fileIn = new FileInputStream("tasks.bin");
//         ObjectInputStream read = new ObjectInputStream(fileIn)) {
      
//       tasksList = (TasksList) read.readObject();
      
//             // Populate the tasks ArrayList from tasksList
//       tasks = new ArrayList<>(tasksList.getTaskList());
    
//     }       catch (FileNotFoundException e) {
//             System.out.println("File not found, or could not be opened");
//           System.exit(1);
//     }       catch (IOException e) {
//             System.out.println("IO Error reading file");
//             e.printStackTrace();
//           System.exit(1);
//     }       catch (ClassNotFoundException e) {
//             System.out.println("Class Not Found");
//             e.printStackTrace();
//           System.exit(1);
//       }
//   }
  
//   pu    blic void writeTasks() { (FileOutputStream fileOut = new FileOutputStream("tasks.xml");
//         PrintWriter write = new PrintWriter(fileOut)) {
      
//             write.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
//             write.println("<tasks>");
//       fo        r (Task task : tasks) {
//                 write.println("<task>");
//                 write.println("<name>" + task.getName() + "</name>");
//                 write.println("<type>" + task.getType() + "</type>");
//                 write.println("<points>" + task.getPoints() + "</points>");
//         write.println("<isComplete>" + task.isCompleteTask() + "</isComplete>");
        
//                 Date completeDate = task.getCompleteDate();
//         if           (completeDate != null) {
//                   write.println("<completeDate>" + completeDate.toString() + "</completeDate>");
//         }           else {
//                   write.println("<completeDate>null</completeDate>");
//         }
        
//               write.println("</task>");
//             }
//           write.println("</tasks>");
//     }       catch (FileNotFoundException e) {
//             System.out.println("File not found.");
//           System.exit(1);
//     }       catch (IOException e) {
//           throw new RuntimeException(e);
//       }
//   }
// }
