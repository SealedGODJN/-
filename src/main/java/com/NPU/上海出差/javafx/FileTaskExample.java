package com.NPU.上海出差.javafx;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ProgressBarTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileTaskExample extends Application {

    private static final Random RNG = new Random();

    private static final int MAX_THREADS = 4;

    private final Executor exec = Executors.newFixedThreadPool(MAX_THREADS, runnable -> {
        Thread t = new Thread(runnable);
        t.setDaemon(true);
        return t;
    });

    @Override
    public void start(Stage primaryStage) {

        // table to display all tasks:
        TableView<FileProcessingTask> table = new TableView<>();

        TableColumn<FileProcessingTask, File> fileColumn = new TableColumn<>("File");
        fileColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<File>(cellData.getValue().getFile()));
        fileColumn.setCellFactory(col -> new TableCell<FileProcessingTask, File>() {
            @Override
            public void updateItem(File file, boolean empty) {
                super.updateItem(file, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(file.getName());
                }
            }
        });
        fileColumn.setPrefWidth(200);

        TableColumn<FileProcessingTask, Worker.State> statusColumn = new TableColumn<>("Status");
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().stateProperty());
        statusColumn.setPrefWidth(100);

        TableColumn<FileProcessingTask, Double> progressColumn = new TableColumn<>("Progress");
        progressColumn.setCellValueFactory(cellData -> cellData.getValue().progressProperty().asObject());
        progressColumn.setCellFactory(ProgressBarTableCell.forTableColumn());
        progressColumn.setPrefWidth(100);

        TableColumn<FileProcessingTask, Long> resultColumn = new TableColumn<>("Result");
        resultColumn.setCellValueFactory(cellData -> cellData.getValue().valueProperty());
        resultColumn.setPrefWidth(100);

        TableColumn<FileProcessingTask, FileProcessingTask> cancelColumn = new TableColumn<>("Cancel");
        cancelColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<FileProcessingTask>(cellData.getValue()));
        cancelColumn.setCellFactory(col -> {
            TableCell<FileProcessingTask, FileProcessingTask> cell = new TableCell<>();
            Button cancelButton = new Button("Cancel");
            cancelButton.setOnAction(e -> cell.getItem().cancel());

            // listener for disabling button if task is not running:
            ChangeListener<Boolean> disableListener = (obs, wasRunning, isNowRunning) ->
                    cancelButton.setDisable(!isNowRunning);

            cell.itemProperty().addListener((obs, oldTask, newTask) -> {
                if (oldTask != null) {
                    oldTask.runningProperty().removeListener(disableListener);
                }
                if (newTask == null) {
                    cell.setGraphic(null);
                } else {
                    cell.setGraphic(cancelButton);
                    cancelButton.setDisable(!newTask.isRunning());
                    newTask.runningProperty().addListener(disableListener);
                }
            });

            return cell;
        });
        cancelColumn.setPrefWidth(100);

        table.getColumns().addAll(Arrays.asList(fileColumn, statusColumn, progressColumn, resultColumn, cancelColumn));

        Button cancelAllButton = new Button("Cancel All");
        cancelAllButton.setOnAction(e ->
                table.getItems().stream().filter(Task::isRunning).forEach(Task::cancel));

        Button newTasksButton = new Button("Process files");
        FileChooser chooser = new FileChooser();
        newTasksButton.setOnAction(e -> {
            List<File> files = chooser.showOpenMultipleDialog(primaryStage);
            if (files != null) {
                files.stream().map(FileProcessingTask::new).peek(exec::execute).forEach(table.getItems()::add);
            }
        });

        HBox controls = new HBox(5, newTasksButton, cancelAllButton);
        controls.setAlignment(Pos.CENTER);
        controls.setPadding(new Insets(10));

        BorderPane root = new BorderPane(table, null, null, controls, null);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static class FileProcessingTask extends Task<Long> {

        private final File file;

        public FileProcessingTask(File file) {
            this.file = file;
        }

        public File getFile() {
            return file;
        }

        @Override
        public Long call() throws Exception {

            // just to show you can return the result of the computation:
            long fileLength = file.length();

            // dummy processing, in real life read file and do something with it:
            int delay = RNG.nextInt(50) + 50;
            for (int i = 0; i < 100; i++) {
                Thread.sleep(delay);
                updateProgress(i, 100);

                // check for cancellation and bail if cancelled:
                if (isCancelled()) {
                    updateProgress(0, 100);
                    break;
                }
            }

            return fileLength;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
