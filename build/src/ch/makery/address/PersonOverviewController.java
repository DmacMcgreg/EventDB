package ch.makery.address;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import ch.makery.address.model.Event;
import ch.makery.address.model.Location;
import ch.makery.address.model.Person;
import ch.makery.address.util.CalendarUtil;

/**
 * The controller for the overview with address table and details view.
 * 
 * @author DAVID MCGREGOR 100851551
 */
public class PersonOverviewController {
	@FXML
	private TableView<Event> eventTable;
	@FXML
	private TableColumn<Event, String> eventNameColumn;
	@FXML
	private TableColumn<Event, String> eventTypeColumn;
	@FXML
	private TableColumn<Event, String> eventDateColumn;
	@FXML
	private Label eventNameLabel;
	@FXML
	private Label eventTypeLabel;
	@FXML
	private Label streetLabel;
	@FXML
	private Label eventTimeLabel;
	@FXML
	private Label eventLocationLabel;
	@FXML
	private Label eventDateLabel;
	@FXML
	private TextArea output;
	
	ObservableList<String> options = 
		    FXCollections.observableArrayList(
		        "Show each event with its location",
		        "Show the attendee list for the Bluesfest event",
		        "List each event whose price is below $300",
		        "List each event that is located in Ottawa",
		        "List all venues whose capacity exceeds 500",
		        "List all attendees of an event whose age is less than 30",
		        "List all concert events",
		        "List all events that start at or after 8PM",
		        "Get all events on Bank Street with their attendees",
		        "Get all events in the month of May"
		    );
	private String[] queries = new String[11];
	@FXML
	private ComboBox queryBox;

	// Reference to the main application
	private MainApp mainApp;

	/**
	 * The constructor. The constructor is called before the initialize()
	 * method.
	 */
	public PersonOverviewController() {
	}

	@SuppressWarnings("unchecked")
	@FXML
	private void initialize() {
		// Initialize the person table
		eventNameColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("name"));
		eventTypeColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("type"));
		eventDateColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("dateString"));
		// Auto resize columns
		eventTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		queryBox.setItems(options);
		// clear person
		showPersonDetails(null);
		
		
		
		// Listen for selection changes
		eventTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Event>() {

			@Override
			public void changed(ObservableValue<? extends Event> observable,
					Event oldValue, Event newValue) {
				showEventDetails(newValue);
			}
		});
		
		queryBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override 
            public void changed(ObservableValue ov, String t, String t1) { 
            	int selectedIndex = queryBox.getSelectionModel().getSelectedIndex();
                try {
					handleQuery(selectedIndex);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}              
            }    
        });
		
		
		queries[0] = "SELECT e.* ,v.* FROM events as e JOIN hostedat as h ON e.eventid = h.eventid JOIN venues as v ON h.venueid = v.venueid";
		queries[1] = "SELECT e.* ,p.* FROM events as e JOIN isAttending as h ON e.eventid = h.eventid AND e.name = 'Bluesfest' JOIN persons as p ON h.personid = p.personid";
		queries[2] = "SELECT e.name	FROM events as e WHERE price < 300";
		queries[3] = "SELECT e.name	FROM events as e JOIN hostedat as h ON e.eventid = h.eventid JOIN venues as v ON h.venueid = v.venueid AND v.city = 'Ottawa'";
		queries[4] = "SELECT v.name FROM venues as v WHERE v.capacity > 500";
		queries[5] = "SELECT e.* ,p.* FROM events as e JOIN isAttending as h ON e.eventid = h.eventid AND e.name = 'Bluesfest' JOIN persons as p ON h.personid = p.personid AND p.age < 30";
		queries[6] = "SELECT e.name	FROM events as e WHERE e.type = 'Concert'";
		queries[7] = "SELECT e.name	FROM events as e WHERE e.time >= 8";
		queries[8] = "SELECT e.* ,p.* FROM events as e JOIN hostedat as h ON e.eventid = h.eventid JOIN venues as v ON h.venueid = v.venueid JOIN isAttending as a ON e.eventid = a.eventid AND v.street LIKE '%Bank%' JOIN persons as p ON a.personid = p.personid";
		queries[9] = "SELECT e.* FROM events as e WHERE e.eventdate LIKE '%May%'";
		
		
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

		// Add observable list data to the table
		eventTable.setItems(mainApp.getEventData());
	}
	
	/**
	 * Fills all text fields to show details about the person.
	 * If the specified person is null, all text fields are cleared.
	 * 
	 * @param person the person or null
	 */
	private void showPersonDetails(Person person) {
		/*if (person != null) {
			eventNameLabel.setText(person.getFirstName());
			eventTypeLabel.setText(person.getLastName());
			Label.setText(person.getStreet());
			postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
			cityLabel.setText(person.getCity());
			birthdayLabel.setText(CalendarUtil.format(person.getBirthday()));
		} else {
			firstNameLabel.setText("");
			lastNameLabel.setText("");
			streetLabel.setText("");
			postalCodeLabel.setText("");
			cityLabel.setText("");
			birthdayLabel.setText("");
		}*/
	}
	
	private void showEventDetails(Event event) {
		if (event != null) {
			eventNameLabel.setText(event.getName());
			eventTypeLabel.setText(event.getType());
			
			eventLocationLabel.setText(event.getLocation().getStreet() + " , " + event.getLocation().getCity() + " , " + event.getLocation().getCountry());
			eventDateLabel.setText(event.getDateString());
		} else {
			eventNameLabel.setText("");
			eventTypeLabel.setText("");
			
			eventTimeLabel.setText("");
			eventLocationLabel.setText("");
			eventDateLabel.setText("");
		}
	}
	
	@FXML
	private void handleQuery(int q) throws SQLException {
		System.out.println("" + q);
		switch(q)
		{
		case 0: handleQueryOne(q); break;
		case 1: handleQueryTwo(q); break;
		case 2: handleQueryThree(q); break;
		case 3: handleQueryFour(q); break;
		case 4: handleQueryFive(q); break;
		case 5: handleQuerySix(q); break;
		case 6: handleQuerySeven(q); break;
		case 7: handleQueryEight(q); break;
		case 8: handleQueryNine(q); break;
		case 9: handleQueryTen(q); break;
		
		}
	}
	
	private void handleQueryOne(int q) throws SQLException {
		Connection database = mainApp.getDatabase();
		Statement stat = mainApp.getStat();
		output.setText("");
		ResultSet rs = stat.executeQuery(queries[q]);
        while (rs.next()) {
        	
        	output.appendText(rs.getString(3)  + " - " + rs.getString(10)+ " - " + rs.getString(11));
        	output.appendText(System.getProperty("line.separator"));
        	
        }
        rs.close(); //close the query result table
	}
	
	private void handleQueryTwo(int q) throws SQLException {
		Connection database = mainApp.getDatabase();
		Statement stat = mainApp.getStat();
		output.setText("");
		ResultSet rs = stat.executeQuery(queries[q]);
        while (rs.next()) {
        	//output.appendText(rs.getString("name")  + " - " + rs.getString(10)+ " - " + rs.getString(11));
        	//" + " AND e.name = " + eventNameLabel.getText() + " 
        	output.appendText(rs.getString("firstName"));
        	output.appendText(System.getProperty("line.separator"));	        	
        }
        rs.close(); //close the query result table
	}
	private void handleQueryThree(int q) throws SQLException {
		Connection database = mainApp.getDatabase();
		Statement stat = mainApp.getStat();
		output.setText("");
		ResultSet rs = stat.executeQuery(queries[q]);
        while (rs.next()) {
        	output.appendText(rs.getString("name"));
        	output.appendText(System.getProperty("line.separator"));	        	
        }
        rs.close(); //close the query result table
	}
	private void handleQueryFour(int q) throws SQLException {
		Connection database = mainApp.getDatabase();
		Statement stat = mainApp.getStat();
		output.setText("");
		ResultSet rs = stat.executeQuery(queries[q]);
        while (rs.next()) {
        	output.appendText(rs.getString("name"));
        	output.appendText(System.getProperty("line.separator"));		        	
        }
        rs.close(); //close the query result table
	}
	private void handleQueryFive(int q) throws SQLException {
		Connection database = mainApp.getDatabase();
		Statement stat = mainApp.getStat();
		output.setText("");
		ResultSet rs = stat.executeQuery(queries[q]);
        while (rs.next()) {
        	output.appendText(rs.getString("name"));
        	output.appendText(System.getProperty("line.separator"));		        	
        }
        rs.close(); //close the query result table
	}
	private void handleQuerySix(int q) throws SQLException {
		Connection database = mainApp.getDatabase();
		Statement stat = mainApp.getStat();
		output.setText("");
		ResultSet rs = stat.executeQuery(queries[q]);
        while (rs.next()) {
        	output.appendText(rs.getString("firstName"));
        	output.appendText(System.getProperty("line.separator"));        	
        }
        rs.close(); //close the query result table
	}
	private void handleQuerySeven(int q) throws SQLException {
		Connection database = mainApp.getDatabase();
		Statement stat = mainApp.getStat();
		output.setText("");
		ResultSet rs = stat.executeQuery(queries[q]);
        while (rs.next()) {
        	output.appendText(rs.getString("name"));
        	output.appendText(System.getProperty("line.separator"));	        	
        }
        rs.close(); //close the query result table
	}
	private void handleQueryEight(int q) throws SQLException {
		Connection database = mainApp.getDatabase();
		Statement stat = mainApp.getStat();
		output.setText("");
		ResultSet rs = stat.executeQuery(queries[q]);
        while (rs.next()) {
        	output.appendText(rs.getString("name"));
        	output.appendText(System.getProperty("line.separator"));        	
        }
        rs.close(); //close the query result table
	}
	private void handleQueryNine(int q) throws SQLException {
		Connection database = mainApp.getDatabase();
		Statement stat = mainApp.getStat();
		output.setText("");
		ResultSet rs = stat.executeQuery(queries[q]);
        while (rs.next()) {
        	output.appendText(rs.getString("name") + " - " + rs.getString("firstName"));
        	output.appendText(System.getProperty("line.separator")); 	        	
        }
        rs.close(); //close the query result table
	}
	private void handleQueryTen(int q) throws SQLException {
		Connection database = mainApp.getDatabase();
		Statement stat = mainApp.getStat();
		output.setText("");
		ResultSet rs = stat.executeQuery(queries[q]);
        while (rs.next()) {
        	output.appendText(rs.getString("name") + " - " + rs.getString("eventDate"));
        	output.appendText(System.getProperty("line.separator"));	        	
        }
        rs.close(); //close the query result table
	}
	
	
	
	/**
	 * Called when the user clicks on the delete button.
	 */
	@FXML
	private void handleDeletePerson() {
		int selectedIndex = eventTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			eventTable.getItems().remove(selectedIndex);
		} else {
			// Nothing selected
			Dialogs.showWarningDialog(mainApp.getPrimaryStage(),
					"Please select a person in the table.",
					"No Person Selected", "No Selection");
		}
	}
	
	/**
	 * Called when the user clicks the new button.
	 * Opens a dialog to edit details for a new person.
	 */
	@FXML
	private void handleNewPerson() {
		Person tempPerson = new Person();
		boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
		if (okClicked) {
			mainApp.getPersonData().add(tempPerson);
		}
	}
	
	/**
	 * Called when the user clicks the edit button.
	 * Opens a dialog to edit details for the selected person.
	 */
	@FXML
	private void handleEditPerson() {
		Event selectedEvent = eventTable.getSelectionModel().getSelectedItem();
		if (selectedEvent != null) {
			boolean okClicked = mainApp.showEventEditDialog(selectedEvent);
			if (okClicked) {
				refreshPersonTable();
				showEventDetails(selectedEvent);
			}
			
		} else {
			// Nothing selected
			Dialogs.showWarningDialog(mainApp.getPrimaryStage(),
					"Please select a person in the table.",
					"No Person Selected", "No Selection");
		}
	}
	
	/**
	 * Refreshes the table. This is only necessary if an item that is already in
	 * the table is changed. New and deleted items are refreshed automatically.
	 * 
	 * This is a workaround because otherwise we would need to use property
	 * bindings in the model class and add a *property() method for each
	 * property. Maybe this will not be necessary in future versions of JavaFX
	 * (see http://javafx-jira.kenai.com/browse/RT-22599)
	 */
	private void refreshPersonTable() {
		int selectedIndex = eventTable.getSelectionModel().getSelectedIndex();
		eventTable.setItems(null);
		eventTable.layout();
		eventTable.setItems(mainApp.getEventData());
		// Must set the selected index again (see http://javafx-jira.kenai.com/browse/RT-26291)
		eventTable.getSelectionModel().select(selectedIndex);
	}
}