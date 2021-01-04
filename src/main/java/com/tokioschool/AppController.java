package com.tokioschool;

import com.tokioschool.comparator.ComparatorNoteForDate;
import com.tokioschool.db.NoteDao;
import com.tokioschool.db.TaskDao;
import com.tokioschool.domain.Note;
import com.tokioschool.domain.Task;
import com.tokioschool.properties.PropertiesAPP;
import com.tokioschool.thread.DownloadListNotes;
import com.tokioschool.thread.DownloadListTasks;
import com.tokioschool.thread.TaskShowAlert;
import com.tokioschool.util.DateUtil;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.stream.Collectors;

import static com.tokioschool.properties.PropertiesAPP.*;

public class AppController implements Initializable {

    @FXML private ImageView ArrowOptions;
    @FXML private ImageView ArrowNote;
    @FXML private ImageView ArrowReminder;
    @FXML private ImageView ArrowTaskList;
    @FXML private ImageView ArrowSearch;
    @FXML private ImageView PanelShowButtonEdit;
    @FXML private ImageView PanelShowButtonSave;

    @FXML private AnchorPane PanelOptions;
    @FXML private AnchorPane PanelNote;
    @FXML private AnchorPane PanelReminder;
    @FXML private AnchorPane PanelTaskList;
    @FXML private AnchorPane PanelSearch;
    @FXML private AnchorPane PanelShow;
    @FXML private AnchorPane PanelState;
    @FXML private AnchorPane PanelNewTask;
    @FXML private AnchorPane PanelWarning;

    @FXML private Label LabelState;
    @FXML private Label LabelOptions;
    @FXML private Label LabelInformation;
    @FXML private Label LabelLanguage;
    @FXML private Label LabelTheme;
    @FXML private Label LabelVersion;
    @FXML private Label LabelInfo1;
    @FXML private Label LabelInfo2;
    @FXML private Label LabelNote;
    @FXML private Label LabelTitleNote;
    @FXML private Label LabelReminder;
    @FXML private Label LabelTitleReminder;
    @FXML private Label LabelDateReminder;
    @FXML private Label LabelTaskList;
    @FXML private Label LabelNewTask;
    @FXML private Label LabelTitleNewTask;
    @FXML private Label LabelSearch;
    @FXML private Label LabelQuestionNotify;

    @FXML private TextArea TextAreaNote;
    @FXML private TextArea TextAreaReminder;
    @FXML private TextArea TextShowDescription;

    @FXML private TextField TextTitleNote;
    @FXML private TextField TextTitleReminder;
    @FXML private TextField TextSearchTitle;
    @FXML private TextField TextSearchDescription;
    @FXML private TextField TextShowTitle;
    @FXML private TextField TextShowDate;
    @FXML private TextField TextTitleNewTask;

    @FXML private ListView<Task> ListViewTaskList;
    @FXML private ListView<Note> ListViewSearch;

    @FXML private ComboBox<String> ComboBoxLanguage;
    @FXML private ComboBox<String> ComboBoxTheme;

    @FXML private DatePicker SelectDateReminder;
    @FXML private DatePicker DateShowEdit;

    @FXML private CheckBox CheckBoxNewTask;

    @FXML private Button ButtonCancelDelete;
    @FXML private Button ButtonDeleteNote;
    @FXML private Button ButtonDeleteTask;
    @FXML private Button ButtonApplyChanges;
    @FXML private Button ButtonResetTask;

    @FXML private ToggleButton ToggleButtonNotificationDelete;

    ObservableList<String> comboBoxLanguageContent = FXCollections.observableArrayList(
            LANGUAGE_1, LANGUAGE_2
    );

    ObservableList<String> comboBoxThemeContent = FXCollections.observableArrayList(
            "Theme 1", "Theme 2", "Theme 3", "Theme 4", "Theme 5", "Theme 6", "Theme 7", "Theme 8"
    );

    private List<Note> notes;
    private List<Task> tasks;
    private ResourceBundle res;


    //Funciones barra superior
    @FXML
    public void onOptionsButtonClicked() {
        applyChanges();
        if(PanelOptions.isVisible()) {
            PanelOptions.setVisible(false);
            ArrowOptions.setVisible(false);
        }else {
            PanelOptions.setVisible(true);
            ArrowOptions.setVisible(true);
        }
        PanelNote.setVisible(false);
        ArrowNote.setVisible(false);
        PanelReminder.setVisible(false);
        ArrowReminder.setVisible(false);
        PanelTaskList.setVisible(false);
        ArrowTaskList.setVisible(false);
        PanelSearch.setVisible(false);
        ArrowSearch.setVisible(false);
        PanelShow.setVisible(false);
        PanelNewTask.setVisible(false);
    }

    @FXML
    public void onNoteButtonClicked(){
        applyChanges();
        if(PanelNote.isVisible()) {
            PanelNote.setVisible(false);
            ArrowNote.setVisible(false);
        }else {
            PanelNote.setVisible(true);
            ArrowNote.setVisible(true);
        }
        PanelOptions.setVisible(false);
        ArrowOptions.setVisible(false);
        PanelReminder.setVisible(false);
        ArrowReminder.setVisible(false);
        PanelTaskList.setVisible(false);
        ArrowTaskList.setVisible(false);
        PanelSearch.setVisible(false);
        ArrowSearch.setVisible(false);
        PanelShow.setVisible(false);
        PanelNewTask.setVisible(false);

    }

    @FXML
    public void onReminderButtonClicked() {
        applyChanges();
        if(PanelReminder.isVisible()) {
            PanelReminder.setVisible(false);
            ArrowReminder.setVisible(false);
        }else {
            PanelReminder.setVisible(true);
            ArrowReminder.setVisible(true);
        }
        PanelNote.setVisible(false);
        ArrowNote.setVisible(false);
        PanelOptions.setVisible(false);
        ArrowOptions.setVisible(false);
        PanelTaskList.setVisible(false);
        ArrowTaskList.setVisible(false);
        PanelSearch.setVisible(false);
        ArrowSearch.setVisible(false);
        PanelShow.setVisible(false);
        PanelNewTask.setVisible(false);

    }

    @FXML
    public void onTaskListButtonClicked() {
        applyChanges();
        if(PanelTaskList.isVisible()) {
            PanelTaskList.setVisible(false);
            ArrowTaskList.setVisible(false);
        }else {
            PanelTaskList.setVisible(true);
            ArrowTaskList.setVisible(true);
            downloadListTasks();
        }
        PanelNote.setVisible(false);
        ArrowNote.setVisible(false);
        PanelReminder.setVisible(false);
        ArrowReminder.setVisible(false);
        PanelOptions.setVisible(false);
        ArrowOptions.setVisible(false);
        PanelSearch.setVisible(false);
        ArrowSearch.setVisible(false);
        PanelShow.setVisible(false);
        PanelNewTask.setVisible(false);

    }

    @FXML
    public void onSearchButtonClicked() {
        applyChanges();
        if(PanelSearch.isVisible()) {
            PanelSearch.setVisible(false);
            ArrowSearch.setVisible(false);
        }else {
            PanelSearch.setVisible(true);
            ArrowSearch.setVisible(true);
            downloadListNotes();
        }
        PanelNote.setVisible(false);
        ArrowNote.setVisible(false);
        PanelReminder.setVisible(false);
        ArrowReminder.setVisible(false);
        PanelTaskList.setVisible(false);
        ArrowTaskList.setVisible(false);
        PanelOptions.setVisible(false);
        ArrowOptions.setVisible(false);
        PanelShow.setVisible(false);
        PanelNewTask.setVisible(false);

    }

    @FXML
    public void onExitButtonClicked() {

        try {
            PropertiesAPP.saveLastConnection();
        } catch (IOException ioException) {
            showAlert(res.getString("error_save_properties"));
            if(DEBUG_MODE){
                ioException.printStackTrace();
            }
        }
        Platform.exit();
        System.exit(0);
    }


    //Funciones panel de opciones
    @FXML
    public void onApplyChangesButton() {
        if(ComboBoxTheme.getValue() != null) {
            switch (ComboBoxTheme.getValue()){
                case "Theme 1":
                    CURRENT_THEME = COLOR_THEME_1;
                    break;
                case "Theme 2":
                    CURRENT_THEME = COLOR_THEME_2;
                    break;
                case "Theme 3":
                    CURRENT_THEME = COLOR_THEME_3;
                    break;
                case "Theme 4":
                    CURRENT_THEME = COLOR_THEME_4;
                    break;
                case "Theme 5":
                    CURRENT_THEME = COLOR_THEME_5;
                    break;
                case "Theme 6":
                    CURRENT_THEME = COLOR_THEME_6;
                    break;
                case "Theme 7":
                    CURRENT_THEME = COLOR_THEME_7;
                    break;
                case "Theme 8":
                    CURRENT_THEME = COLOR_THEME_8;
                    break;
            }
            changeTheme();
        }
        if(ComboBoxLanguage.getValue() != null) {
            switch (ComboBoxLanguage.getValue()) {
                case LANGUAGE_1:
                    CURRENT_LANGUAGE = LANGUAGE_1;
                    break;
                case LANGUAGE_2:
                    CURRENT_LANGUAGE = LANGUAGE_2;
                    break;
            }
            changeLanguage();
            changeLanguageComponents();
        }

        if(ToggleButtonNotificationDelete.isSelected()) {
            NOTIFICATION_DELETE = NOTIFICATION_ON;
        } else {
            NOTIFICATION_DELETE = NOTIFICATION_OFF;
        }

        try {
            PropertiesAPP.saveProperties();
        } catch (IOException ioException) {
            showAlert(res.getString("error_save_properties"));
            if(DEBUG_MODE){
                ioException.printStackTrace();
            }
        }

        showConfirm(res.getString("confirm_properties"));
    }


    //Funciones panel de notas
    @FXML
    public void onSaveNoteButton() {
        String title = TextTitleNote.getText();
        String description = TextAreaNote.getText();
        Note note;
        if(TextTitleNote.getText().equals("")) {
            showAlert(res.getString("error_title_missing"));
            return;
        }
        if(TextAreaNote.getText().equals("")) {
            note = new Note(title);
        }else {
            note = new Note(title, description);
        }
        NoteDao dbNote = new NoteDao();
        try {
            dbNote.connect();
            dbNote.addNote(note);
            dbNote.close();
        } catch (SQLException sqlException) {
            showAlert(res.getString("error_database"));
            if(DEBUG_MODE){
                sqlException.printStackTrace();
            }
        }
        TextAreaNote.clear();
        TextTitleNote.clear();
        showConfirm(res.getString("confirm_save"));
    }


    //Funciones panel de recordatorios
    @FXML
    public void onSaveReminderButton() {
        String title = TextTitleReminder.getText();
        String description = TextAreaReminder.getText();
        LocalDate date;
        Note note;
        if(TextTitleReminder.getText().equals("")) {
            showAlert(res.getString("error_title_missing"));
            return;
        }
        if(SelectDateReminder.getValue() == null) {
            showAlert(res.getString("error_date_missing"));
            return;
        }
        if(LocalDate.now().isAfter(SelectDateReminder.getValue())) {
            showAlert(res.getString("error_date_missing"));
            return;
        }
        date = SelectDateReminder.getValue();
        if(TextAreaReminder.getText().equals((""))) {
            note = new Note(title, date);
        } else {
            note = new Note(title, description, date);
        }
        addNote(note);
        TextAreaReminder.clear();
        TextTitleReminder.clear();
        showConfirm(res.getString("confirm_save"));
    }


    //Funciones panel de lista de tareas

    @FXML
    public void onMarkTaskButton() {
        if(ListViewTaskList.getItems().stream().count() == 0) return;
        if(ListViewTaskList.getSelectionModel().isEmpty()) return;
        Task task = ListViewTaskList.getSelectionModel().getSelectedItem();
        if(task.isFinalize()) return;
        if(!task.isRepeat()) {
            deleteTask(task);
            downloadListTasks();
            return;
        }
        task.finalizeTask();
        editTask(task);
        downloadListTasks();
    }

    @FXML
    public void onUnMarkTaskButton() {
        if(ListViewTaskList.getItems().stream().count() == 0) return;
        if(ListViewTaskList.getSelectionModel().isEmpty()) return;
        Task task = ListViewTaskList.getSelectionModel().getSelectedItem();
        if(!task.isFinalize()) return;
        task.resetTask();
        editTask(task);
        downloadListTasks();
    }

    @FXML
    public void onDeleteTaskButton() {
        if(ListViewTaskList.getItems().stream().count() == 0) return;
        Task task = ListViewTaskList.getSelectionModel().getSelectedItem();
        confirmDelete(task);
    }

    @FXML
    public void onNewTaskButton() {
        PanelNewTask.setVisible(true);
        PanelTaskList.setVisible(false);
    }

    @FXML
    private void onResetDailyTasksButton() {
        for (Task task : tasks) {
            task.resetTask();
            editTask(task);
        }
        downloadListTasks();
    }


    //Funciones panel de nueva tarea

    @FXML
    public void onSaveNewTaskButton() {
        Task task = new Task(TextTitleNewTask.getText(), CheckBoxNewTask.isSelected());
        if(TextTitleNewTask.getText().equals("")) {
            showAlert(res.getString("error_title_missing"));
            return;
        }
        addTask(task);
        TextTitleNewTask.clear();
        CheckBoxNewTask.setSelected(false);
        PanelNewTask.setVisible(false);
        downloadListTasks();
        PanelTaskList.setVisible(true);
        showConfirm(res.getString("confirm_save"));
    }

    @FXML
    public void onDeleteNewTaskButton() {
        PanelNewTask.setVisible(false);
        TextTitleNewTask.clear();
        CheckBoxNewTask.setSelected(false);
        PanelTaskList.setVisible(true);

    }


    //Funciones panel de buscar
    @FXML
    public void searchTitle(){
        String search = TextSearchTitle.getText();
        List<Note> notesFilter = notes.stream()
                .filter(note -> note.getTitle().toLowerCase().trim().contains(search.toLowerCase().trim()))
                .collect(Collectors.toList());
        initListViewSearch(notesFilter);
    }

    public void searchDescription(){
        String search = TextSearchDescription.getText();
        List<Note> notesFilter = notes.stream()
                .filter(note -> note.getDescription().toLowerCase().trim().contains(search.toLowerCase().trim()))
                .collect(Collectors.toList());
        initListViewSearch(notesFilter);
    }

    @FXML
    public void onDeleteSearchButton() {
        if(ListViewSearch.getItems().stream().count() == 0) return;
        if(ListViewSearch.getSelectionModel().isEmpty()) return;
        Note note = ListViewSearch.getSelectionModel().getSelectedItem();
        confirmDelete(note);
    }

    @FXML
    public void onViewSearchButton() {
        if(ListViewSearch.getItems().stream().count() == 0) return;
        if(ListViewSearch.getSelectionModel().isEmpty()) return;
        Note note = ListViewSearch.getSelectionModel().getSelectedItem();
        PanelSearch.setVisible(false);
        PanelShow.setVisible(true);
        TextShowTitle.setText(note.getTitle());

        TextShowDescription.setText(note.getDescription());
        PanelShowButtonEdit.setVisible(true);
        PanelShowButtonSave.setVisible(false);
        TextShowTitle.setEditable(false);
        TextShowDescription.setEditable(false);
        TextShowDate.setVisible(false);
        if(note.getType().equals(TYPE_REMINDER)){
            TextShowDate.setText(DateUtil.localDateFormat(note.getDate()));
            DateShowEdit.setVisible(false);
            TextShowDate.setVisible(true);
        }
    }


    //Funciones panel de mostrar

    @FXML
    public void onDeleteShowButton() {
        Note note = ListViewSearch.getSelectionModel().getSelectedItem();
        confirmDelete(note);
        onSearchButtonClicked();
    }

    @FXML
    public void onEditShowButton() {
        Note note = ListViewSearch.getSelectionModel().getSelectedItem();
        PanelShowButtonEdit.setVisible(false);
        PanelShowButtonSave.setVisible(true);
        TextShowDescription.setEditable(true);
        TextShowTitle.setEditable(true);
        if(note.getType().equals((TYPE_REMINDER))){
            TextShowDate.setVisible(false);
            DateShowEdit.setVisible(true);
        }
    }

    @FXML
    public void onSaveShowButton() {
        Note note = ListViewSearch.getSelectionModel().getSelectedItem();
        Note noteEdit;
        if(TextShowTitle.getText().equals("")){
            showAlert(res.getString("error_title_missing"));
            return;
        }
        if(note.getType().equals(TYPE_REMINDER)){
            LocalDate dateEdit;
            if (DateShowEdit.getValue() != null) {
                dateEdit = DateShowEdit.getValue();
            } else {
                dateEdit = note.getDate();
            }
            if(LocalDate.now().isAfter(dateEdit)){
                showAlert(res.getString("error_date_missing"));
                return;
            }
            noteEdit = new Note(TextShowTitle.getText(), TextShowDescription.getText(), dateEdit);
        } else {
            noteEdit = new Note(TextShowTitle.getText(),TextShowDescription.getText());
        }
        noteEdit.setId(note.getId());
        editNote(noteEdit);
        onSearchButtonClicked();
        showConfirm(res.getString("confirm_edit"));
    }


    //Funciones panel de advertencia
    @FXML
    public void onAcceptButtonForNote(){
        PanelWarning.setVisible(false);
        deleteNote(noteDelete);
        downloadListNotes();
        noteDelete = null;
        ButtonDeleteNote.setVisible(false);
        showConfirm(res.getString("confirm_delete"));

    }

    @FXML
    public void onAcceptButtonForTask(){
        PanelWarning.setVisible(false);
        deleteTask(taskDelete);
        downloadListTasks();
        taskDelete = null;
        ButtonDeleteTask.setVisible(false);
        showConfirm(res.getString("confirm_delete"));
    }

    @FXML
    public  void onCancelButton(){
        PanelWarning.setVisible(false);
        noteDelete = null;
        taskDelete = null;
    }

    private Note noteDelete;
    public void confirmDelete(Note note){
        if(NOTIFICATION_DELETE.equals(NOTIFICATION_ON)){
            PanelWarning.setVisible(true);
            noteDelete = note;
            ButtonDeleteNote.setVisible(true);
        } else {
            deleteNote(note);
            downloadListNotes();
            showConfirm(res.getString("confirm_delete"));
        }
    }

    private Task taskDelete;
    public void confirmDelete(Task task) {
        if(NOTIFICATION_DELETE.equals(NOTIFICATION_ON)){
            PanelWarning.setVisible(true);
            taskDelete = task;
            ButtonDeleteTask.setVisible(true);
        } else{
            deleteTask(task);
            downloadListTasks();
            showConfirm(res.getString("confirm_delete"));
        }
    }

    //Funciones
    int count = 0;
    public void applyChanges() {
        if(count != 0) return;
        changeTheme();
        changeLanguage();
        changeLanguageComponents();
        if(NOTIFICATION_DELETE.equals(NOTIFICATION_ON)) ToggleButtonNotificationDelete.setSelected(true);
        count++;
    }

    public void changeTheme() {
        PanelOptions.setStyle(CHANGE_BACKGROUND_COLOR + CURRENT_THEME);
        PanelNote.setStyle(CHANGE_BACKGROUND_COLOR + CURRENT_THEME);
        PanelReminder.setStyle(CHANGE_BACKGROUND_COLOR + CURRENT_THEME);
        PanelTaskList.setStyle(CHANGE_BACKGROUND_COLOR + CURRENT_THEME);
        PanelSearch.setStyle(CHANGE_BACKGROUND_COLOR + CURRENT_THEME);
        PanelShow.setStyle(CHANGE_BACKGROUND_COLOR + CURRENT_THEME);
        PanelNewTask.setStyle(CHANGE_BACKGROUND_COLOR + CURRENT_THEME);
    }

    public void changeLanguage() {
        Locale locale;
        if(CURRENT_LANGUAGE.equals(LANGUAGE_1)){
            locale = new Locale("en");
        }else{
            locale = new Locale("es");
        }
        res = ResourceBundle.getBundle("text", locale);
    }

    private void changeLanguageComponents() {
        ButtonDeleteTask.setText(res.getString("delete"));
        ButtonDeleteNote.setText(res.getString("delete"));
        ButtonCancelDelete.setText(res.getString("cancel"));
        ButtonApplyChanges.setText(res.getString("apply_changes"));
        ButtonResetTask.setText((res.getString("reset_tasks")));
        ToggleButtonNotificationDelete.setText(res.getString("notification_delete"));
        LabelOptions.setText(res.getString("options"));
        LabelInformation.setText(res.getString("information"));
        LabelLanguage.setText(res.getString("language"));
        LabelTheme.setText(res.getString("theme"));
        LabelVersion.setText(res.getString("version"));
        LabelInfo1.setText(res.getString("info1"));
        LabelInfo2.setText(res.getString("info2"));
        LabelNote.setText(res.getString("note"));
        LabelTitleNote.setText(res.getString("title"));
        LabelReminder.setText(res.getString("reminder"));
        LabelTitleReminder.setText(res.getString("title"));
        LabelDateReminder.setText(res.getString("date"));
        LabelTaskList.setText(res.getString("task_list"));
        LabelNewTask.setText(res.getString("new_task"));
        LabelTitleNewTask.setText(res.getString("title"));
        LabelSearch.setText(res.getString("search"));
        LabelQuestionNotify.setText(res.getString("question_notify"));
        TextTitleNote.setPromptText(res.getString("write_the_title"));
        TextAreaNote.setPromptText(res.getString("write_the_note"));
        TextTitleReminder.setPromptText(res.getString("write_the_title"));
        TextAreaReminder.setPromptText(res.getString("write_the_note"));
        SelectDateReminder.setPromptText(res.getString("select_date"));
        TextTitleNewTask.setPromptText(res.getString("write_the_title"));
        CheckBoxNewTask.setText(res.getString("select_repeat"));
        TextSearchTitle.setPromptText(res.getString("search_for_title"));
        TextSearchDescription.setPromptText(res.getString("search_for_description"));
        DateShowEdit.setPromptText(res.getString("select_date"));
    }

    private void downloadListNotes() {
        FutureTask futureTask = new FutureTask(new DownloadListNotes());
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(futureTask);
        try {
            notes = (List<Note>) futureTask.get();
        } catch (InterruptedException interruptedException) {
            showAlert(res.getString("error_database"));
            if(DEBUG_MODE){
                interruptedException.printStackTrace();
            }
        } catch (ExecutionException e) {
            showAlert(res.getString("error_database"));
            if(DEBUG_MODE){
                e.printStackTrace();
            }
        } finally {
            initListViewSearch();
        }
    }

    private void initListViewSearch() {
        notes.sort(new ComparatorNoteForDate());
        ObservableList<Note> observableList = FXCollections.observableList(notes);
        ListViewSearch.setItems(observableList);
    }

    private void initListViewSearch(List notes){
        notes.sort(new ComparatorNoteForDate());
        ObservableList<Note> observableList = FXCollections.observableList(notes);
        ListViewSearch.setItems(observableList);
    }

    private void addNote(Note note) {
        NoteDao dbNote = new NoteDao();
        try {
            dbNote.connect();
            dbNote.addNote(note);
            dbNote.close();
        } catch (SQLException sqlException) {
            showAlert(res.getString("error_database"));
            if(DEBUG_MODE){
                sqlException.printStackTrace();
            }
        }
    }

    private void deleteNote(Note note) {
        NoteDao dbNote = new NoteDao();
        try {
            dbNote.connect();
            dbNote.deleteNote(note);
            dbNote.close();
        } catch (SQLException sqlException) {
            showAlert(res.getString("error_database"));
            if(DEBUG_MODE){
                sqlException.printStackTrace();
            }
        }
    }

    private void editNote(Note note) {
        NoteDao dbNote = new NoteDao();
        try {
            dbNote.connect();
            dbNote.editNote(note);
            dbNote.close();
        } catch (SQLException sqlException) {
            showAlert(res.getString("error_database"));
            if(DEBUG_MODE){
                sqlException.printStackTrace();
            }
        }
    }

    private void downloadListTasks() {
        FutureTask futureTask = new FutureTask(new DownloadListTasks());
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(futureTask);
        try {
            tasks = (List<Task>) futureTask.get();
        } catch (InterruptedException interruptedException) {
            showAlert(res.getString("error_database"));
            if(DEBUG_MODE){
                interruptedException.printStackTrace();
            }
        } catch (ExecutionException e) {
            showAlert(res.getString("error_database"));
            if(DEBUG_MODE){
                e.printStackTrace();
            }
        } finally {
            initListViewTask();
        }
    }

    private void initListViewTask() {
        ObservableList<Task> observableList = FXCollections.observableList(tasks);
        ListViewTaskList.setItems(observableList);
    }

    private void addTask(Task task) {
        TaskDao dbTask = new TaskDao();
        try {
            dbTask.connect();
            dbTask.addTask(task);
            dbTask.close();
        } catch (SQLException sqlException) {
            showAlert(res.getString("error_database"));
            if(DEBUG_MODE){
                sqlException.printStackTrace();
            }
        }
    }

    private void deleteTask(Task task) {
        TaskDao dbTask = new TaskDao();
        try {
            dbTask.connect();
            dbTask.deleteTask(task);
            dbTask.close();
        } catch (SQLException sqlException) {
            showAlert(res.getString("error_database"));
            if(DEBUG_MODE){
                sqlException.printStackTrace();
            }
        }
    }

    private void editTask(Task task) {
        TaskDao dbTask = new TaskDao();
        try {
            dbTask.connect();
            dbTask.editTask(task);
            dbTask.close();
        } catch (SQLException sqlException) {
            showAlert(res.getString("error_database"));
            if(DEBUG_MODE){
                sqlException.printStackTrace();
            }
        }
    }

    public void showAlert(String message) {
        PanelState.setStyle(CHANGE_BACKGROUND_COLOR + COLOR_ALERT);
        LabelState.setText(message);
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(new TaskShowAlert(PanelState));
    }

    public void showConfirm(String message) {
        PanelState.setStyle(CHANGE_BACKGROUND_COLOR + COLOR_CONFIRM);
        LabelState.setText(message);
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(new TaskShowAlert(PanelState));
    }

    //Funciones sobreescritas

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ComboBoxLanguage.setItems(comboBoxLanguageContent);
        ComboBoxTheme.setItems(comboBoxThemeContent);
    }
}
