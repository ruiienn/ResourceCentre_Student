import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ResourceCentreTest {
	// prepare test data
	private Camcorder cc1;
	private Camcorder cc2;
	private Camcorder cc3;
	private Chromebook cb1;
	private Chromebook cb2;
	private Chromebook cb3;

	private ArrayList<Camcorder> camcorderList;
	private ArrayList<Chromebook> chromebookList;

	public ResourceCentreTest() {
		super();
	}

	@Before
	public void setUp() throws Exception {
		// prepare test data
		cc1 = new Camcorder("CC0011", "Nikon HDSLR", 40);
		cc2 = new Camcorder("CC0012", "Sony DSC-RX100M7", 20);
		cc3 = new Camcorder("CC0013", "Panasonic DSC-RX100M7", 30);
		cb1 = new Chromebook("CB0011", "My Google Chromebook 1st", "Mac OS");
		cb2 = new Chromebook("CB0012", "Samsung Chromebook 4+", "Win 10");
		cb3 = new Chromebook("CB0013", "HUAWEI Magicbook 100+", "Mac 10");

		camcorderList = new ArrayList<Camcorder>();
		chromebookList = new ArrayList<Chromebook>();
	}

	@Test
	public void testAddCamcorder() {
		// Item list is not null and it is empty
		assertNotNull("Test if there is a valid Camcorder arraylist to add to", camcorderList);
		assertEquals("Test that the Camcorder arraylist is empty.", 0, camcorderList.size());

		// Given an empty list, after adding 1 item, the size of the list is 1
		ResourceCentre.addCamcorder(camcorderList, cc1);
		assertEquals("Test that the Camcorder arraylist size is 1.", 1, camcorderList.size());

		// Add another item
		ResourceCentre.addCamcorder(camcorderList, cc2);
		assertEquals("Test that the Camcorder arraylist size is now 2.", 2, camcorderList.size());
		// The item just added is the same as the last item in the list
		assertSame("Test that Camcorder is added to the end of the list.", cc2, camcorderList.get(1));

		// Add an item that already exists in the list
		ResourceCentre.addCamcorder(camcorderList, cc2);
		assertEquals("Test that the Camcorder arraylist size is unchanged.", 2, camcorderList.size());

		// Add an item that has missing details
		Camcorder cc_missing = new Camcorder("CC0014", "", 60);
		ResourceCentre.addCamcorder(camcorderList, cc_missing);
		assertEquals("Test that the Camcorder arraylist size is unchanged.", 2, camcorderList.size());
	}

	@Test
	public void testAddChromebook() {
		assertNotNull("Test if there is a valid Chromebook arraylist to add to", chromebookList);
		assertEquals("Test that the Chromebook arraylist is empty.", 0, chromebookList.size());

		ResourceCentre.addChromebook(chromebookList, cb1);
		assertEquals("Test that the Chromebook arraylist size is 1.", 1, chromebookList.size());

		ResourceCentre.addChromebook(chromebookList, cb2);
		assertEquals("Test that the Chromebook arraylist size is now 2.", 2, chromebookList.size());
		assertSame("Test that Chromebook is added to the end of the list.", cb2, chromebookList.get(1));

		ResourceCentre.addChromebook(chromebookList, cb2);
		assertEquals("Test that the Chromebook arraylist size is unchanged.", 2, chromebookList.size());

		Chromebook cb_missing = new Chromebook("CB0014", "", "Win 10");
		ResourceCentre.addChromebook(chromebookList, cb_missing);
		assertEquals("Test that the Chromebook arraylist size is unchanged.", 2, chromebookList.size());
	}

	@Test
	public void testRetrieveAllCamcorder() {
		assertNotNull("Test if there is a valid Camcorder arraylist to add to", camcorderList);
		assertEquals("Test that the Camcorder arraylist is empty.", 0, camcorderList.size());

		String allCamcorder = ResourceCentre.retrieveAllCamcorder(camcorderList);
		String testOutput = "";
		assertEquals("Test that nothing is displayed", testOutput, allCamcorder);

		ResourceCentre.addCamcorder(camcorderList, cc1);
		ResourceCentre.addCamcorder(camcorderList, cc2);

		assertEquals("Test that the Camcorder arraylist size is 2.", 2, camcorderList.size());

		allCamcorder = ResourceCentre.retrieveAllCamcorder(camcorderList);
		testOutput = String.format("%-10s %-30s %-10s %-10s %-20d\n", "CC0011", "Nikon HDSLR", "Yes", "", 40);
		testOutput += String.format("%-10s %-30s %-10s %-10s %-20d\n", "CC0012", "Sony DSC-RX100M7", "Yes", "", 20);

		assertEquals("Test that the display is correct.", testOutput, allCamcorder);

		cc3.setIsAvailable(false);
		ResourceCentre.addCamcorder(camcorderList, cc3);

		assertEquals("Test that Camcorder arraylist size is 3.", 3, camcorderList.size());
		assertFalse("Test that the last item in the arraylist is not available", camcorderList.get(2).getIsAvailable());

		allCamcorder = ResourceCentre.retrieveAllCamcorder(camcorderList);
		testOutput = String.format("%-10s %-30s %-10s %-10s %-20d\n", "CC0011", "Nikon HDSLR", "Yes", "", 40);
		testOutput += String.format("%-10s %-30s %-10s %-10s %-20d\n", "CC0012", "Sony DSC-RX100M7", "Yes", "", 20);

		assertEquals("Test that the display is correct.", testOutput, allCamcorder);
	}

	@Test
	public void testRetrieveAllChromebook() {
		assertNotNull("Test if there is a valid Chromebook arraylist to add to", chromebookList);
		assertEquals("Test that the Chromebook arraylist is empty.", 0, chromebookList.size());

		String allChromebook = ResourceCentre.retrieveAllChromebook(chromebookList);
		String testOutput = "";

		assertEquals("Test that nothing is displayed", testOutput, allChromebook);

		ResourceCentre.addChromebook(chromebookList, cb1);
		ResourceCentre.addChromebook(chromebookList, cb2);

		assertEquals("Test that the Chromebook arraylist size is 2.", 2, chromebookList.size());

		allChromebook = ResourceCentre.retrieveAllChromebook(chromebookList);
		testOutput = String.format("%-10s %-30s %-10s %-10s %-20s\n", "CB0011", "My Google Chromebook 1st", "Yes", "", "Mac OS");
		testOutput += String.format("%-10s %-30s %-10s %-10s %-20s\n", "CB0012", "Samsung Chromebook 4+", "Yes", "", "Win 10");

		assertEquals("Test that the display is correct.", testOutput, allChromebook);

		cb3.setIsAvailable(false);
		ResourceCentre.addChromebook(chromebookList, cb3);

		assertEquals("Test that Chromebook arraylist size is 3.", 3, chromebookList.size());
		assertFalse("Test that the last item in the arraylist is not available", chromebookList.get(2).getIsAvailable());

		allChromebook = ResourceCentre.retrieveAllChromebook(chromebookList);
		testOutput = String.format("%-10s %-30s %-10s %-10s %-20s\n", "CB0011", "My Google Chromebook 1st", "Yes", "", "Mac OS");
		testOutput += String.format("%-10s %-30s %-10s %-10s %-20s\n", "CB0012", "Samsung Chromebook 4+", "Yes", "", "Win 10");

		assertEquals("Test that the display is correct.", testOutput, allChromebook);
	}

	@Test
	public void testDoLoanCamcorder() {
		assertNotNull("Test if there is a valid Camcorder arraylist to loan from", camcorderList);
		ResourceCentre.addCamcorder(camcorderList, cc1);

		Boolean ok = ResourceCentre.doLoanCamcorder(camcorderList, "CC0011", "8-8-2020");
		assertTrue("Test if an available item is okay to loan?", ok);
		assertFalse(camcorderList.get(0).getIsAvailable());
		assertEquals("8-8-2020", camcorderList.get(0).getDueDate());

		cc2.setIsAvailable(false);
		ResourceCentre.addCamcorder(camcorderList, cc2);
		assertFalse("Test that there is an item not available", camcorderList.get(1).getIsAvailable());

		ok = ResourceCentre.doLoanCamcorder(camcorderList, "CC0012", "8-8-2020");
		assertFalse("Test that the loan fails.", ok);

		ok = ResourceCentre.doLoanCamcorder(camcorderList, "CC0016", "8-8-2020");
		assertFalse("Test that the loan fails.", ok);

		ResourceCentre.addCamcorder(camcorderList, cc3);
		assertTrue("Test that there is an item available", camcorderList.get(2).getIsAvailable());

		ok = ResourceCentre.doLoanCamcorder(camcorderList, "CC0013", "");
		assertFalse("Test that the loan fails.", ok);
	}

	@Test
	public void testDoLoanChromebook() {
		assertNotNull("Test if there is a valid Chromebook arraylist to loan from", chromebookList);
		ResourceCentre.addChromebook(chromebookList, cb1);

		Boolean ok = ResourceCentre.doLoanChromebook(chromebookList, "CB0011", "8-8-2020");
		assertTrue("Test if an available item is okay to loan?", ok);
		assertFalse(chromebookList.get(0).getIsAvailable());
		assertEquals("8-8-2020", chromebookList.get(0).getDueDate());

		cb2.setIsAvailable(false);
		ResourceCentre.addChromebook(chromebookList, cb2);
		assertFalse("Test that there is an item not available", chromebookList.get(1).getIsAvailable());

		ok = ResourceCentre.doLoanChromebook(chromebookList, "CB0012", "8-8-2020");
		assertFalse("Test that the loan fails.", ok);

		ok = ResourceCentre.doLoanChromebook(chromebookList, "CB0016", "8-8-2020");
		assertFalse("Test that the loan fails.", ok);

		ResourceCentre.addChromebook(chromebookList, cb3);
		assertTrue("Test that there is an item available", chromebookList.get(2).getIsAvailable());

		ok = ResourceCentre.doLoanChromebook(chromebookList, "CB0013", "");
		assertFalse("Test that the loan fails.", ok);
	}

	@Test
	public void testDoReturnCamcorder() {
		assertNotNull("Test if there is a valid Camcorder arraylist to add to", camcorderList);
		ResourceCentre.addCamcorder(camcorderList, cc1);
		Boolean ok = ResourceCentre.doLoanCamcorder(camcorderList, "CC0011", "8-8-2020");
		assertTrue("Test if CC0011 is successfully loaned out.", ok);
		Boolean isReturned = ResourceCentre.doReturnCamcorder(camcorderList, "CC0011");
		assertTrue("Test if the return of CC0011 is successful.", isReturned);
		assertTrue("Test that CC0011 is now available.", camcorderList.get(0).getIsAvailable());

		isReturned = ResourceCentre.doReturnCamcorder(camcorderList, "CC0011");
		assertFalse("Test that the return fails.", isReturned);

		isReturned = ResourceCentre.doReturnCamcorder(camcorderList, "CC0013");
		assertFalse("Test that the return fails.", isReturned);
	}

	@Test
	public void testDoReturnChromebook() {
		assertNotNull("Test if there is a valid Chromebook arraylist to add to", chromebookList);
		ResourceCentre.addChromebook(chromebookList, cb1);
		Boolean ok = ResourceCentre.doLoanChromebook(chromebookList, "CB0011", "8-8-2020");
		assertTrue("Test if CB0011 is successfully loaned out.", ok);
		Boolean isReturned = ResourceCentre.doReturnChromebook(chromebookList, "CB0011");
		assertTrue("Test if the return of CB0011 is successful.", isReturned);
		assertTrue("Test that CB0011 is now available.", chromebookList.get(0).getIsAvailable());

		isReturned = ResourceCentre.doReturnChromebook(chromebookList, "CB0011");
		assertFalse("Test that the return fails.", isReturned);

		isReturned = ResourceCentre.doReturnChromebook(chromebookList, "CB0013");
		assertFalse("Test that the return fails.", isReturned);
	}

	@After
	public void tearDown() throws Exception {
		cc1 = null;
		cc2 = null;
		cc3 = null;
		cb1 = null;
		cb2 = null;
		cb3 = null;
		camcorderList = null;
		chromebookList = null;
	}
}
