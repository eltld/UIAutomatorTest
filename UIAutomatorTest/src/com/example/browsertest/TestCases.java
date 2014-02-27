package com.example.test;

// Import the UiAutomator libraries

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;

import android.os.HandlerThread;
import android.os.RemoteException;

/*
 * App Test activity
 * Main activity which contains the script for automation testing
 * @authors Smriti Tuteja
 */

public class TestCases extends UiAutomatorTestCase {

	int serial_no = 0;
	StringBuffer report = new StringBuffer("");
	String finalReport = null;
	public static int testcases_passed = 0;
	public static int testcases_failed = 0;
	String result = null;
	String value;
	UiObject allAppsButton;
	UiObject appsTab;
	UiScrollable appViews;
	UiSelector selector;
	UiObject appToLaunch;
	Runnable testreport = null;
	String message;
	UiObject omnibox;
	UiObject tab_count;
	UiObject newtab;
	UiObject settings;
	UiObject urlClass;
	String appName = "App_Android";
	String currentDate;
	String appVersion;
	String appBuildType;
	UiObject appBackground;

	public void testDemo() throws UiObjectNotFoundException, RemoteException {

		allAppsButton = new UiObject(new UiSelector().description("Apps"));
		appsTab = new UiObject(new UiSelector().text("Apps"));
		appViews = new UiScrollable(new UiSelector().scrollable(true));
		selector = new UiSelector().className(android.widget.TextView.class
				.getName());
		main_menu = new UiObject(new UiSelector().resourceId("com.example.test.mobile:id/settings_menu"));
		omnibox = new UiObject(new UiSelector().resourceId("com.example.test.mobile:id/url"));
		tab_count = new UiObject(new UiSelector().resourceId("com.example.test.mobile:id/countTab"));
		newtab = new UiObject(
				new UiSelector()
						.resourceId("com.example.test.mobile:id/newTab"));
		settings = new UiObject(new UiSelector().text("Settings"));
		urlClass = new UiObject(
				new UiSelector().className("android.widget.EditText"));
	
		//reporting method
		testreport = new Runnable() {
			public void run() {
				String logData = null;
				String logName = null;
				String logVersion = null;
				String logBuildType = null;
				String logDate = null;
				String logTestCases_passed = null;
				String logTestCases_failed = null;
				String serverUrl = "http://abc.com/androidlogs/testindex.php";
				finalReport = report.toString();
				logData = finalReport;
				logName = appName;
				logVersion = appVersion;
				logBuildType = appBuildType;
				logDate = currentDate;
				logTestCases_passed = Integer.toString(testcases_passed);
				logTestCases_failed = Integer.toString(testcases_failed);

				try {

					HttpClient httpClient = new DefaultHttpClient();
					HttpPost request = new HttpPost(serverUrl);
					ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
					postParameters.add(new BasicNameValuePair("finalReport",
							logData));
					postParameters.add(new BasicNameValuePair("appName",
							logName));
					postParameters.add(new BasicNameValuePair("appVersion",
							logVersion));
					postParameters.add(new BasicNameValuePair("appBuildType",
							logBuildType));
					postParameters.add(new BasicNameValuePair("currentDate",
							logDate));
					postParameters.add(new BasicNameValuePair(
							"testcases_passed", logTestCases_passed));
					postParameters.add(new BasicNameValuePair(
							"testcases_failed", logTestCases_failed));
					UrlEncodedFormEntity form = new UrlEncodedFormEntity(
							postParameters);
					request.setEntity(form);
					HttpResponse response = httpClient.execute(request);
					int i = response.getStatusLine().getStatusCode();
					System.out.println("server response  " + i);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};

		report.delete(0, report.length());

		// take current date
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		currentDate = sdf.format(date);
		System.out.println("Today's date : " + currentDate);


		// Email Signup
		appendSerialNumber();
		System.out.println("Email Sign Up");
		report.append("<td> Email Sign Up </td>");
		UiObject loginbutton = new UiObject(
				new UiSelector().description("loginbutton"));
		try {
			assertTrue("Email Sign Up Fail", loginbutton.exists());
			loginbutton.click();
			new UiObject(new UiSelector().text("SIGN UP")).click();
			//email field
			UiObject signupemail = new UiObject(
					new UiSelector().description("signupemail"));
			signupemail.clearTextField();
			signupemail.setText("my@email.com");

			//password field
			UiObject psswrd = new UiObject(
					new UiSelector().description("password"));
			psswrd.click();
			psswrd.setText("illusion");
			getUiDevice().pressBack();
			new UiObject(new UiSelector().text("Create an Account"))
					.clickAndWaitForNewWindow();
			sleep(50000);
			new UiObject(new UiSelector().text("OK")).click();
			report.append("<td>Email Signup Success</td>");
			testCaseSuccessMessage();
			sleep(5000);

		} catch (AssertionError ex) {
			AppendMessageWithTags(ex);
		}


		// Check App shortcut on home screen
		System.out.println("Checking app shortcut");
		appendSerialNumber();
		report.append("<td> Check app shortcut on home screen </td>");
		sleep(3000);
		getUiDevice().pressHome();
		sleep(3000);
		try {
			UiObject AppHome = new UiObject(
					new UiSelector().text("App_Android"));
			assertTrue("App shortcut not created", AppHome.exists());
			report.append("<td> App shortcut created </td>");
			testCaseSuccessMessage();
		} catch (AssertionError ex) {
			AppendMessageWithTags(ex);
		}

		// Relaunch app from background
		relaunchAppBackground();

		// Take app version
		System.out.println("Taking app version");
		
		// open menu
		openMenu();

		// open settings
		settings.click();
		sleep(5000);
		UiScrollable scrollSettingsPage = new UiScrollable(
				new UiSelector().scrollable(true));
		scrollSettingsPage.scrollForward();
		sleep(2000);
		appVersion = new UiObject(
				new UiSelector().resourceId("android:id/summary")).getText();
		System.out.println(appVersion);
		String build_type_check = appVersion.substring(1, 2);
		if (build_type_check.equals("0")) {
			appBuildType = "Development";
		} else if (build_type_check.equals("1")) {
			appBuildType = "Production";
		}
		getUiDevice().pressBack();

	
		// Open new tab
		launchNewTab();

		// Visiting some url
		appendSerialNumber();
		System.out.println("URL Visit");
		report.append("<td>URL Visit</td>");

		try {
			assertTrue("URL Visit Fail", omnibox.exists());
			omnibox.click();
			sleep(5000);
			omnibox.setText("google.com");
			sleep(5000);
			//taking x,y coordinates of the 'Go' button keyboard
			getUiDevice().click(735, 1135);
			sleep(20000);
			report.append("<td>Success</td>");
			testCaseSuccessMessage();
			getCurrencyDetails();

		} catch (AssertionError ex) {
			AppendMessageWithTags(ex);
		}


		// Email Broadcast
		appendSerialNumber();
		System.out.println("Email Broadcast");
		report.append("<td>Email Broadcast : </br></br>");
		report.append(" i) Open Gmail </br>");
		report.append(" ii) Send test report email </td>");
		getUiDevice().pressHome();
		new UiObject(new UiSelector().description("Apps")).click();
		appViews.setAsHorizontalList();
		try {
			//opening gmail
			appToLaunch = appViews.getChildByText(selector, "Gmail");
			assertNotNull("Gmail App not found", appToLaunch);
			if (appToLaunch != null)
				appToLaunch.clickAndWaitForNewWindow();
			report.append("<td></br></br> Gmail opened </br>");
			
			//compose button
			new UiObject(new UiSelector().description("Compose")).click();
			UiObject receipent = new UiObject(
					new UiSelector().description("To"));
			receipent.click();
			//adding receipent
			receipent.setText("abc@xyz.com");
			sleep(6000);
			//setting subject
			UiObject emailSubject = new UiObject(
					new UiSelector().text("Subject"));
			emailSubject.click();
			emailSubject.setText("App Android " + appVersion
					+ " Automation Test Report");
			UiObject composeEmail = new UiObject(
					new UiSelector().text("Compose email"));
			String emailBody = "Hey\n\nPlease find below the Test Report of "
					+ appName
					+ " latest "
					+ appBuildType
					+ " build "
					+ appVersion
					+ " -\n\nhttp://abc.com/reports/TestReports/App_Android_v0.0.1_Development.html\n\n";
			composeEmail.setText(emailBody);
			new UiObject(new UiSelector().description("Send")).click();
			report.append("Email sent </td>");
			testCaseSuccessMessage();
		} catch (AssertionError ex) {
			AppendMessageWithTags(ex);
		}

		report.append("</table></body></html>");
		if (testreport != null)
			testreport.run();

	}



	//appending serial number in report
	public void appendSerialNumber() {
		report.append("<tr><td>");
		serial_no++;
		report.append(serial_no);
		report.append("</td>");
	}

	//relaunching app from background
	public void relaunchAppBackground() throws RemoteException,
			UiObjectNotFoundException {
		appendSerialNumber();
		System.out.println("Relaunch app in background");
		report.append("<td> Relaunch app from background </td>");
		getUiDevice().pressRecentApps();
		sleep(5000);
		appBackground = new UiObject(new UiSelector().description("App_Android"));
		try {
			assertTrue("App doesn't exist in background",
					appBackground.exists());
			appBackground.click();
			sleep(6000);
			report.append("<td> App relaunched from background </td>");
			testCaseSuccessMessage();

		} catch (AssertionError ex) {
			AppendMessageWithTags(ex);
		}
	}

	//launching new tab
	public void launchNewTab() throws UiObjectNotFoundException {

		System.out.println("Launch new tab");
		sleep(3000);
		try {
			assertTrue("Could not open new tab", tab_count.exists());
			tab_count.click();
			sleep(2000);
			newtab.click();
		} catch (AssertionError ex) {
			appendSerialNumber();
			report.append("<td>Open New tab </td>");
			AppendMessageWithTags(ex);
		}
	}

	public void openMenu() throws UiObjectNotFoundException {

		System.out.println("Open  menu");
		sleep(3000);
		try {
			assertTrue("Menu not found", main_menu.exists());
			main_menu.click();
		} catch (AssertionError ex) {
			appendSerialNumber();
			report.append("<td>Open Menu </td>");
			AppendMessageWithTags(ex);
		}
	}

	//failure message
	public void testCaseFailureMessage() {
		report.append("<td class=\"centered-cell\" bgcolor=\"#ff6347\">Failure</td></tr>");
		testcases_failed++;
	}

	//success message
	public void testCaseSuccessMessage() {
		report.append("<td class=\"centered-cell\" bgcolor=\"#98fb98\">Success</td></tr>");
		testcases_passed++;
	}

	public void AppendMessageWithTags(AssertionError ex) {
		message = ex.getMessage();
		report.append("<td>");
		report.append(message);
		report.append("</td>");
		testCaseFailureMessage();
		sleep(5000);
		checkCrash();
	}

	public void AppendMessageAlone(AssertionError ex) {
		message = ex.getMessage();
		report.append(message);
		sleep(5000);
		checkCrash();
	}
	
	
	//check ANR
	public void checkANR() {
		UiObject perkANR = new UiObject(
				new UiSelector()
						.text("App_Android isn't responding. Do you want to close it?"));
		if (perkANR.exists()) {
			System.out.println("ANR");
			report.append("</table>");
			report.append("<h1 style=\"text-align:center;background-color:#ff6347;\">App_Android  is not responding : ANR!!</h1></body></html>");
			if (testreport != null) {
				testreport.run();
			}
			System.exit(0);
		}
	}
	
	
	//checking for app crash
	public void checkCrash() {
		UiObject Crash = new UiObject(
				new UiSelector().text("Unfortunately, App_Android has stopped."));
		if (Crash.exists()) {
			System.out.println("CRASH");
			report.append("</td>");
			testCaseFailureMessage();
			report.append("</table><h1 style=\"text-align:center;background-color:#ff6347;\">APP CRASHED!!</h1></body></html>");
			if (testreport != null) {
				testreport.run();
			}
			System.exit(0);
		}
	}
}
