//package com.java.concurrent.EDAP20221103;
//
//import javafx.application.Application;
//import javafx.beans.value.ChangeListener;
//import javafx.beans.value.ObservableValue;
//import javafx.concurrent.ScheduledService;
//import javafx.concurrent.Task;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.ProgressBar;
//import javafx.scene.layout.FlowPane;
//import javafx.scene.layout.HBox;
//import javafx.stage.Stage;
//import javafx.util.Duration;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//
///**
// * @author stone
// */
//
//public class MyApp_ScheduledService extends Application {
//
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        primaryStage.setTitle("Hello World!");
//
//        HBox hBox = new HBox(20);
//
//        Button startBtn = new Button("开始");
//        Button cancelBtn = new Button("取消");
//        Button resetBtn = new Button("重置");
//        Button restartBtn = new Button("重启");
//        ProgressBar progressBar = new ProgressBar(0);
//        progressBar.setPrefWidth(200);
//
//        Label l1 = new Label("state");
//        Label l2 = new Label("value");
//        Label l3 = new Label("title");
//        Label l4 = new Label("message");
//
//        FlowPane root = new FlowPane();
//        hBox.getChildren().addAll(startBtn, cancelBtn, restartBtn, resetBtn, progressBar, l1, l2, l3, l4);
//        root.getChildren().add(hBox);
//        primaryStage.setScene(new Scene(root, 700, 300));
//        primaryStage.show();
//
//        MyScheduledService mss = new MyScheduledService();
//        //等待5s开始、
//
//        mss.setDelay(Duration.seconds(5));
//        //程序执行时间
//        mss.setPeriod(Duration.seconds(10));
//        //启动失败重新启动
//        mss.setRestartOnFailure(true);
//        //程序启动失败后重新启动次数
//        mss.setMaximumFailureCount(4);
//
//        startBtn.setOnAction(event -> {
//            mss.start();
//            System.out.println("开始");
//        });
//        cancelBtn.setOnAction(event -> {
//            mss.cancel();
//            System.out.println("取消");
//        });
//        resetBtn.setOnAction(event -> {
//            mss.reset();
//            System.out.println("重置");
//        });
//        restartBtn.setOnAction(event -> {
//            mss.restart();
//            System.out.println("重启");
//        });
//
//        // lambda 写法
//        mss.valueProperty().addListener(((observable, oldValue, newValue) -> {
//            if (newValue != null) {
//                l2.setText(String.valueOf(newValue));
//            }
//        }));
//
////        mss.valueProperty().addListener(new ChangeListener<Number>() {
////            @Override
////            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
////                if (newValue != null) {
////                    l2.setText(String.valueOf(newValue));
////                }
////            }
////        });
//
//        mss.lastValueProperty().addListener(new ChangeListener<Number>() {
//            @Override
//            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//                if (newValue != null) {
//                    System.out.println("lastValue=" + newValue.intValue());
//                }
//            }
//        });
//
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}
//
//class MyScheduledService extends ScheduledService<Number> {
//
//    int sum = 0;
//
//    @Override
//    protected Task<Number> createTask() {
//
//        System.out.println("createTask()");
//
//        Task<Number> task = new Task<Number>() {
//
//            @Override
//            protected void updateValue(Number value) {
//                super.updateValue(value);
//                if (value.intValue() == 10) {
//                    MyScheduledService.this.cancel();
//                    System.out.println("任务取消");
//                }
//            }
//
//            @Override
//            protected Number call() throws Exception {
//                this.updateTitle("copy");
//                FileInputStream fis = new FileInputStream(new File("C:\\Users\\HJN13\\Desktop\\黄江南-请假.docx"));
//                FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\HJN13\\Desktop\\西北工业大学 横线16开.docx"));
//                //获取字节长
//                double max = fis.available();
//                byte[] readbyte = new byte[10000];
//                int i = 0;
//                //目前完成进度
//                double sum = 0;
//                //进度
//                double progress = 0;
//                while ((i = fis.read(readbyte, 0, readbyte.length)) != -1) {
//
//                    /*if (this.isCancelled()){
//                        break;
//                    }*/
//                    fos.write(readbyte, 0, i);
//                    sum = sum + i;
//                    //当前大小和总共大小
//                    this.updateProgress(sum, max);
//                    progress = sum / max;
//                    /*System.out.println(progress);*/
//                    Thread.sleep(50);
//                    if (progress < 0.5) {
//                        this.updateMessage("请耐心等待");
//                    } else if (progress < 0.8) {
//                        this.updateMessage("马上就好");
//                    } else if (progress < 1) {
//                        this.updateMessage("即将完成");
//                    } else if (progress >= 1) {
//                        this.updateMessage("搞定了");
//                    }
//                }
//
//                fis.close();
//                fos.close();
////                sum=sum+1;
////                System.out.println(sum);
//                return sum;
//            }
//        };
//        return task;
//    }
//}
