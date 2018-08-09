package edu.pdx.cs410J.kata.client;

import com.google.common.annotations.VisibleForTesting;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.shared.UmbrellaException;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A basic GWT class that makes sure that we can send an Phone Bill back from the server
 */
public class PacManGwt implements EntryPoint {
  private final Alerter alerter;
  private final PacManServiceAsync phoneBillService;
  private final Logger logger;


  public PacManGwt() {
    this(new Alerter() {
      @Override
      public void alert(String message) {
        Window.alert(message);
      }
    });
  }

  @VisibleForTesting
  PacManGwt(Alerter alerter) {
    this.alerter = alerter;
    this.phoneBillService = GWT.create(PacManService.class);
    this.logger = Logger.getLogger("phoneBill");
    Logger.getLogger("").setLevel(Level.INFO);  // Quiet down the default logging
  }

  private void alertOnException(Throwable throwable) {
    Throwable unwrapped = unwrapUmbrellaException(throwable);
    StringBuilder sb = new StringBuilder();
    sb.append(unwrapped.toString());
    sb.append('\n');

    for (StackTraceElement element : unwrapped.getStackTrace()) {
      sb.append("  at ");
      sb.append(element.toString());
      sb.append('\n');
    }

    this.alerter.alert(sb.toString());
  }

  private Throwable unwrapUmbrellaException(Throwable throwable) {
    if (throwable instanceof UmbrellaException) {
      UmbrellaException umbrella = (UmbrellaException) throwable;
      if (umbrella.getCauses().size() == 1) {
        return unwrapUmbrellaException(umbrella.getCauses().iterator().next());
      }

    }

    return throwable;
  }

  private void addWidgets(VerticalPanel panel) {
    Label welcome  = new Label("Edit Pac-Man board");
    panel.add(welcome);

    TextArea editor = new TextArea();
    panel.add(createSettingsPanel(editor));

    editor.setValue(
      "+----+\n" +
      "+    +\n" +
      "+    +\n" +
      "+ <  +\n" +
      "+    +\n" +
      "+----+\n".replace(' ', '.')
    );
    setEditorDimensions(6, 6, editor);
    panel.add(editor);
  }

  private HorizontalPanel createSettingsPanel(TextArea editor) {
    TextBox height = new TextBox();
    TextBox width = new TextBox();

    HorizontalPanel settings = new HorizontalPanel();
    settings.add(new Label("Width:"));

    width.setMaxLength(2);
    width.setVisibleLength(2);
    width.setValue("6");
    width.addChangeHandler(new ChangeHandler() {
      @Override
      public void onChange(ChangeEvent event) {
        setEditorDimensions(width, height, editor);
      }
    });

    settings.add(width);

    settings.add(new Label("Height"));

    height.setMaxLength(2);
    height.setVisibleLength(2);
    height.setValue("6");
    height.addChangeHandler(new ChangeHandler() {
      @Override
      public void onChange(ChangeEvent event) {
        setEditorDimensions(width, height, editor);
      }
    });

    settings.add(height);
    settings.add(new Button("Play Game"));

    return settings;
  }

  private void setEditorDimensions(TextBox width, TextBox height, TextArea editor) {
    int h = getIntValue(height, editor.getVisibleLines());
    int w = getIntValue(width, editor.getCharacterWidth());
    setEditorDimensions(w, h, editor);
  }

  private void setEditorDimensions(int width, int height, TextArea editor) {
    editor.setVisibleLines(height);
    editor.setCharacterWidth(width);
  }

  private int getIntValue(TextBox textBox, int currentValue) {
    String value = textBox.getValue();
    try {
      return Integer.parseInt(value);

    } catch (NumberFormatException ex) {
      alerter.alert("Invalid integer: " + value);
      return currentValue;
    }
  }

  @Override
  public void onModuleLoad() {
    setUpUncaughtExceptionHandler();

    // The UncaughtExceptionHandler won't catch exceptions during module load
    // So, you have to set up the UI after module load...
    Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
      @Override
      public void execute() {
        setupUI();
      }
    });
  }


  private void setupUI() {
    RootPanel rootPanel = RootPanel.get();
    VerticalPanel panel = new VerticalPanel();
    rootPanel.add(panel);

    addWidgets(panel);
  }

  private void setUpUncaughtExceptionHandler() {
    GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {
      @Override
      public void onUncaughtException(Throwable throwable) {
        alertOnException(throwable);
      }
    });
  }

  @VisibleForTesting
  interface Alerter {
    void alert(String message);
  }

}
