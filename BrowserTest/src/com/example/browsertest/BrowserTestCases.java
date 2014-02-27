package com.example.browsertest;

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
 * Browser Test activity
 * Main activity which contains the script for automation testing
 * @authors Smriti Tuteja
 */

public class BrowserTestCases extends UiAutomatorTestCase {

	int serial_no = 0;
	StringBuffer report = new StringBuffer("");
	String finalReport = null;
	public static final String third_party_app_url = "abc.com/androidlogs/links.html";
	public static final String private_browsing_key = "web";
	public static int testcases_passed = 0;
	public static int testcases_failed = 0;
	String result = null;
	String value;
	UiObject allAppsButton;
	UiObject appsTab;
	UiScrollable appViews;
	UiSelector selector;
	UiObject appToLaunch;
	UiObject main_menu;
	UiObject continueWalkthrough;
	UiObject tableLayoutClass;
	UiObject currencyClass;
	UiObject yellowCurrency;
	UiObject greyCurrency;
	UiObject greenCurrency;
	UiObject loggedOutCurrencyPoints;
	UiObject badge;
	String get_notifications;
	int notification;
	Runnable testreport = null;
	String message;
	UiObject omnibox;
	UiObject tab_count;
	UiObject newtab;
	UiObject orbmenu;
	UiObject travel = null;
	int points;
	String currency_value;
	UiObject myBookmarks;
	UiObject settings;
	UiObject history;
	UiObject signupCurrencyPoints;
	UiObject urlClass;
	UiObject closeToast;
	UiObject saveBookmark;
	UiObject persistentSearch;
	UiObject share;
	UiObject checkin;
	UiObject twitterShare;
	UiObject fbShare;
	String appName = "Browser_Android";
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
		tableLayoutClass = new UiScrollable(
				new UiSelector().className("android.widget.TableLayout"));
		persistentSearch = new UiObject(
				new UiSelector().description("persistentsearch"));
		main_menu = new UiObject(new UiSelector().resourceId("com.example.browsertest.mobile:id/settings_menu"));
		continueWalkthrough = new UiObject(
				new UiSelector().description("continue"));
		currencyClass = new UiObject(
				new UiSelector().className("android.widget.RelativeLayout"));
		yellowCurrency = currencyClass.getChild(new UiSelector()
				.description("yellow"));
		greyCurrency = currencyClass.getChild(new UiSelector()
				.description("grey"));
		greenCurrency = currencyClass.getChild(new UiSelector()
				.description("green"));
		badge = currencyClass.getChild(new UiSelector().description("badge"));
		omnibox = new UiObject(new UiSelector().resourceId("com.example.browsertest.mobile:id/url"));
		tab_count = new UiObject(new UiSelector().resourceId("com.example.browsertest.mobile:id/countTab"));
		newtab = new UiObject(
				new UiSelector()
						.resourceId("com.example.browsertest.mobile:id/newTab"));
		orbmenu = new UiObject(new UiSelector().description("orbmenu"));
		myBookmarks = new UiObject(new UiSelector().text("My Bookmarks"));
		settings = new UiObject(new UiSelector().text("Settings"));
		history = new UiObject(new UiSelector().text("HISTORY"));
		loggedOutCurrencyPoints = new UiObject(new UiSelector().text("100"));
		signupCurrencyPoints = new UiObject(new UiSelector().text("50"));
		urlClass = new UiObject(
				new UiSelector().className("android.widget.EditText"));
		closeToast = new UiObject(new UiSelector().description("closebutton"));
		saveBookmark = new UiObject(new UiSelector().text("Save"));
		persistentSearch = new UiObject(
				new UiSelector().description("persistentsearch"));
		share = new UiObject(new UiSelector().description("share"));
		checkin = new UiObject(new UiSelector().description("checkin"));
		twitterShare = new UiObject(
				new UiSelector().description("twitter_share"));
		fbShare = new UiObject(new UiSelector().description("fb_share"));

		testreport = new Runnable() {
			public void run() {
				String logData = null;
				String logName = null;
				String logVersion = null;
				String logBuildType = null;
				String logDate = null;
				String logTestCases_passed = null;
				String logTestCases_failed = null;
				String serverUrl = "http://abc.com/androidlogs/browserindex.php";
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

		// Launch Browser
		launchBrowser();

		// Walkthrough
		appendSerialNumber();
		System.out.println("Walkthrough Tour");
		report.append("<td>Walkthrough Tour</td>");
		UiObject getStarted = new UiObject(new UiSelector().text("Get Started"));
		try {
			assertTrue("First Walkthrough Failure", getStarted.exists());
			getStarted.click();
			sleep(4000);
			report.append("<td>First Walkthrough Success</br>");

			try {
				assertTrue("No category selected - Second Walkthrough Failure",
						tableLayoutClass.exists());
				tableLayoutClass.getChild(
						new UiSelector().description("business")).click();
				tableLayoutClass.getChild(new UiSelector().description("home"))
						.click();
				UiScrollable scrollCategories = new UiScrollable(
						new UiSelector().scrollable(true));
				scrollCategories.scrollForward();
				tableLayoutClass.getChild(
						new UiSelector().description("popular")).click();

				continueWalkthrough.clickAndWaitForNewWindow();
				report.append("Second Walkthrough Success</br>");

				try {
					assertTrue(
							"No category selected - Third Walkthrough Failure",
							tableLayoutClass.exists());
					tableLayoutClass.getChild(
							new UiSelector().description("fashion")).click();
					tableLayoutClass.getChild(
							new UiSelector().description("gifts")).click();
					continueWalkthrough.clickAndWaitForNewWindow();
					report.append("Third Walkthrough Success</br>");

					UiObject female = new UiObject(
							new UiSelector().text("Female"));
					try {
						assertTrue("No Gender selected - ", female.exists());
						female.click();
						UiObject agegroup2 = new UiObject(
								new UiSelector().text("19-25 years old"));
						assertTrue("No Age selected - ", agegroup2.exists());
						agegroup2.click();
						continueWalkthrough.click();
						report.append("DOB Walkthrough Success</td> ");
						testCaseSuccessMessage();

					} catch (AssertionError ex) {
						AppendMessageAlone(ex);
						report.append("DOB Walkthrough Failure</td>");
						testCaseFailureMessage();
					}

				} catch (AssertionError ex) {
					AppendMessageAlone(ex);
					report.append("</td>");
					testCaseFailureMessage();
				}

			} catch (AssertionError ex) {
				AppendMessageAlone(ex);
				report.append("</td>");
				testCaseFailureMessage();
			}

		} catch (AssertionError ex) {
			AppendMessageWithTags(ex);
		}

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
			UiObject signupemail = new UiObject(
					new UiSelector().description("signupemail"));
			signupemail.clearTextField();
			signupemail.setText("my@email.com");

			getUiDevice().pressBack();
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

			// Discard orb menu coach mark
			getUiDevice().pressBack();

			sleep(5000);

			// get currency info
			appendSerialNumber();
			report.append("<td>Currency check after Sign Up</td><td>");

			if (signupCurrencyPoints.exists() && badge.exists()
					&& greenCurrency.exists()) {
				getCurrencyDetails();
				testCaseSuccessMessage();
			} else {
				getCurrencyDetails();
				testCaseFailureMessage();
			}

		} catch (AssertionError ex) {
			AppendMessageWithTags(ex);
			appendSerialNumber();
			System.out.println("Email Log In");
			report.append("<td> Email Log In </td>");

			try {
				assertTrue("Email Log In Fail",
						loggedOutCurrencyPoints.exists());
				loggedOutCurrencyPoints.click();
				new UiObject(new UiSelector().text("LOG IN")).click();
				UiObject loginemail = new UiObject(
						new UiSelector().description("loginemail"));
				loginemail.clearTextField();
				loginemail.setText("xyz@abc.com");
				getUiDevice().pressBack();
				UiObject psswrd = new UiObject(
						new UiSelector().description("password"));
				psswrd.click();
				psswrd.setText("illusion");
				getUiDevice().pressBack();
				UiObject login = new UiObject(new UiSelector().text("Log In"));
				login.clickAndWaitForNewWindow();
				report.append("<td>Log In Success</td>");
				testCaseSuccessMessage();

				// get currency info
				appendSerialNumber();
				report.append("<td>Currency check after Log In</td><td>");
				sleep(8000);
				getCurrencyDetails();
				testCaseSuccessMessage();

			} catch (AssertionError ex1) {
				AppendMessageWithTags(ex1);
			}
		}

		// Check App shortcut on home screen
		System.out.println("Checking app shortcut");
		appendSerialNumber();
		report.append("<td> Check app shortcut on home screen </td>");
		sleep(3000);
		getUiDevice().pressHome();
		sleep(3000);
		try {
			UiObject BrowserHome = new UiObject(
					new UiSelector().text("Browser"));
			assertTrue("App shortcut not created", BrowserHome.exists());
			report.append("<td> Browser shortcut created </td>");
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

		// Search something
		appendSerialNumber();
		System.out.println("On Search");
		report.append("<td>On Search</td>");
		try {
			assertTrue("Search Fail", omnibox.exists());
			omnibox.click();

			// Discard omnibar search coach mark
			getUiDevice().pressBack();
			getUiDevice().pressBack();

			omnibox.setText("web");
			getUiDevice().click(735, 1135);
			sleep(15000);

			// Discard check-in coach mark
			getUiDevice().pressBack();

			report.append("<td>");

			getCurrencyDetails();
			if (greenCurrency.exists()) {
				testCaseSuccessMessage();
			} else {
				testCaseFailureMessage();
			}

			// close toast
			if (closeToast.exists()) {
				closeToast.click();
			}

		} catch (AssertionError ex) {
			AppendMessageWithTags(ex);
		}

		// Open new tab
		launchNewTab();

		// Direct store visit
		appendSerialNumber();
		System.out.println("Direct Store Visit");
		report.append("<td>Direct Store Visit - 800bear.com : </br></br> i) Currency Check</td>");

		try {
			assertTrue("Direct Visit Fail", omnibox.exists());
			omnibox.click();
			sleep(5000);
			omnibox.setText("800bear.com");
			sleep(5000);
			getUiDevice().click(735, 1135);
			sleep(20000);

			// Discard shopping site coach mark
			getUiDevice().pressBack();

			report.append("<td></br>");
			getCurrencyDetails();
			if ((yellowCurrency.exists()) && (result.contains("true"))) {
				testCaseSuccessMessage();
			} else {
				testCaseFailureMessage();
			}

		} catch (AssertionError ex) {
			AppendMessageWithTags(ex);
		}

		// Open new tab
		launchNewTab();

		// Opening links from third party apps - normal link
		appendSerialNumber();
		System.out.println("Opening third party url");
		report.append("<td>Opening third party url</td>");
		sleep(3000);
		try {
			assertTrue("Could not type in omnibar", omnibox.exists());
			omnibox.click();
			sleep(5000);
			omnibox.setText(third_party_app_url);
			sleep(5000);
			getUiDevice().click(735, 1135);
			sleep(8000);
			getUiDevice().click(257, 252);
			sleep(15000);
			assertTrue("Could not open third party app link", urlClass
					.getText().contains("Apple"));
			report.append("<td> Third party Link opened</td>");
			testCaseSuccessMessage();

		} catch (AssertionError ex) {
			AppendMessageWithTags(ex);
		}

		// launch orb
		orbLaunch();

		// launch shop page
		appendSerialNumber();
		System.out.println("Shop Page Launch");
		report.append("<td>Shop Page Launch</td>");
		UiObject shop = new UiObject(new UiSelector().description("Shop"));
		try {
			assertTrue("Shop menu not found", shop.exists());
			shop.clickAndWaitForNewWindow();
			report.append("<td>Shop page opened successfully</td>");
			testCaseSuccessMessage();

		} catch (AssertionError ex) {
			AppendMessageWithTags(ex);
		}


		// Launch Browser from Persistent Tray
		launchBrowserPersistentSearch();

		// Opening links from third party apps - youtube link
		appendSerialNumber();
		System.out.println("Opening youtube link");
		report.append("<td> Opening youtube link </td>");
		sleep(3000);
		try {
			assertTrue("Could not type in omnibar", omnibox.exists());
			omnibox.setText(third_party_app_url);
			sleep(7000);
			getUiDevice().click(735, 1135);
			sleep(5000);
			getUiDevice().click(117, 233);
			sleep(8000);
			UiObject choseChrome = new UiObject(new UiSelector().text("Chrome"));
			choseChrome.click();
			UiObject Chrome = new UiObject(new UiSelector().text("Always"));
			alwaysChrome.click();
			sleep(8000);
			try {
				assertTrue("Could not open Youtube link", urlClass.getText()
						.contains("youtube"));
				report.append("<td> Third party Youtube link opened</td>");
				testCaseSuccessMessage();
			} catch (AssertionError ex) {
				AppendMessageWithTags(ex);
			}

		} catch (AssertionError ex) {
			AppendMessageWithTags(ex);
		}

		// put app in background
		appendSerialNumber();
		System.out.println("Put app in background");
		report.append("<td> Put app in background </td>");
		getUiDevice().pressHome();
		report.append("<td> App in background </td>");
		testCaseSuccessMessage();

		// relaunch app from background
		relaunchAppBackground();

		// Open new tab
		launchNewTab();

		// Non partner store visit
		appendSerialNumber();
		System.out.println("Non Partner Store Visit");
		report.append("<td>Non Partner Store Visit - Google.com : </br></br> i)Currency Check</td>");

		try {
			assertTrue("Could not type in omnibar", omnibox.exists());
			omnibox.click();
			omnibox.setText("google.com");
			getUiDevice().click(735, 1135);
			report.append("<td></br>");
			getCurrencyDetails();
			if ((greyCurrency.exists()) && (result.contains("false"))) {
				testCaseSuccessMessage();
			} else {
				testCaseFailureMessage();
			}
		} catch (AssertionError ex) {
			AppendMessageWithTags(ex);
		}

		
		// go to tabs view
		tab_count.click();
		//launch browser from search tray
		launchBrowserPersistentSearch();

		// Omnibar store visit
		appendSerialNumber();
		System.out.println("Omnibar Store Visit");
		report.append("<td>Omnibar Store Visit - Basketball Express.com : </br></br> i) Currency Check</td>");

		try {
			assertTrue("Could not type in omnibar", omnibox.exists());
			omnibox.click();
			omnibox.setText("bas");
			sleep(8000);
			getUiDevice().click(322, 208);
			report.append("<td></br>");
			sleep(5000);
			getCurrencyDetails();
			getGreenCurrency();
		} catch (AssertionError ex) {
			AppendMessageWithTags(ex);
		}

		// Check-in
		appendSerialNumber();
		System.out.println("Check-in and Tweet");
		report.append("<td> Check-in and Tweet </td>");
		try {
			assertTrue("Could not click on checkin button", checkin.exists());
			checkin.click();
			sleep(5000);
			new UiObject(new UiSelector().description("cool")).click();
			twitterShare.click();
			sleep(10000);
			report.append("<td> Checked in at basketballexpress.com and shared on Twitter </td>");
			testCaseSuccessMessage();

		} catch (AssertionError ex) {
			AppendMessageWithTags(ex);
		}

		// Open new tab
		launchNewTab();

		// Green injected results Store visit
		appendSerialNumber();
		System.out.println("Green injected results Store Visit");
		report.append("<td>Green injected results Store Visit - Ftd.com : </br></br> i) Currency Check</td>");

		try {
			assertTrue("Could not type in omnibar", omnibox.exists());
			omnibox.click();
			omnibox.setText("ftd");
			sleep(5000);
			getUiDevice().click(735, 1135);
			sleep(8000);
			report.append("<td></br>");
			getUiDevice().click(216, 583);
			sleep(6000);
			getCurrencyDetails();
			getGreenCurrency();
			sleep(15000);
		} catch (AssertionError ex) {
			AppendMessageWithTags(ex);
		}

		// Open new tab
		launchNewTab();

		//Menu
		// Bookmark any site
		appendSerialNumber();
		System.out.println("Bookmark functionality");
		report.append("<td> Bookmark functionality </td>");
		try {
			assertTrue("Could not type in omnibar", omnibox.exists());
			omnibox.click();
			omnibox.setText("google.com");
			sleep(7000);
			report.append("<td>Checking site for bookmark functionality</br>");
			getUiDevice().click(735, 1135);
			sleep(7000);
			UiObject bookmark = new UiObject(
					new UiSelector().description("bookmark"));
			if (bookmark.exists()) {
				bookmark.click();
				report.append("Saving Bookmark</br>");
				if (saveBookmark.exists()) {
					saveBookmark.click();
					report.append("Bookmark Saved</td>");
					testCaseSuccessMessage();
				} else {
					report.append("Bookmark not saved");
					report.append("</br>Bookmark functionality fail </td>");
					testCaseFailureMessage();
				}

			} else {
				report.append("Bookmark icon not found");
				report.append("</br>Bookmark functionality fail </td>");
				testCaseFailureMessage();
			}

		} catch (AssertionError ex) {
			report.append("<td>");
			AppendMessageAlone(ex);
			report.append("</br>Bookmark functionality fail </td>");
			testCaseFailureMessage();
		}

		// Open menu
		openMenu();

		// check bookmark saved or not
		appendSerialNumber();
		System.out.println("Check Bookmark in My Bookmarks section");
		report.append("<td> Check Bookmark in My Bookmarks section </td>");
		try {

			sleep(3000);
			assertTrue("My Bookmarks page not found", myBookmarks.exists());
			myBookmarks.click();
			sleep(30000);
			report.append("<td>Checking My Bookmarks page</br>");
			UiObject bookmark_check_class = new UiObject(new UiSelector()
					.className("android.widget.TextView").index(1));
			if (bookmark_check_class.exists()) {
				String bookmark_check = bookmark_check_class.getText();
				if (bookmark_check.contains("google")) {
					report.append("Bookmark Saved!</td>");
					testCaseSuccessMessage();
					getUiDevice().pressBack();
				} else {
					report.append("Bookmark not Saved!</td>");
					testCaseFailureMessage();
					getUiDevice().pressBack();
				}
			} else {
				report.append("Could not check Bookmark Saved!</td>");
				testCaseFailureMessage();
			}

		} catch (AssertionError ex) {
			report.append("<td>");
			AppendMessageAlone(ex);
			report.append("</br>Bookmark check fail </td>");
			testCaseFailureMessage();
		}

		// Open menu
		openMenu();

		// History
		sleep(3000);
		appendSerialNumber();
		System.out.println("Clear History Check");
		report.append("<td> Clear History Check</td>");

		try {
			assertTrue("My Bookmarks section not found - ",
					myBookmarks.exists());
			myBookmarks.click();
			sleep(30000);
			report.append("<td>My Bookmarks page opened</br>");
			if (history.exists()) {
				history.click();
				sleep(20000);
				report.append("History section opened</br>");
				UiObject clearHistory = new UiObject(
						new UiSelector().text("Clear History"));
				if (clearHistory.exists()) {
					clearHistory.clickAndWaitForNewWindow();
					report.append("Clearing History</br>");
					new UiObject(new UiSelector().text("Clear")).click();
					if (currencyClass.getText().isEmpty()) {
						report.append("History Cleared</td>");
						testCaseSuccessMessage();
						getUiDevice().pressBack();
					} else {
						report.append("Clearing History Failed</td>");
						testCaseFailureMessage();
						getUiDevice().pressBack();
					}
				} else {
					report.append("Clear History button not found</td>");
					testCaseFailureMessage();
					getUiDevice().pressBack();
				}
			} else {
				report.append("History page not found</td>");
				testCaseFailureMessage();
			}

		} catch (AssertionError ex) {
			report.append("<td>");
			AppendMessageAlone(ex);
			report.append("</br> History Check Failure </td>");
			testCaseFailureMessage();
		}

		// Open new tab
		launchNewTab();

		// Open menu
		openMenu();

		// Search engine selection
		appendSerialNumber();
		System.out.println("Search Engine Change Check");
		report.append("<td> Search Engine Change Check</td>");
		try {
			assertTrue("Settings page not found", settings.exists());
			settings.click();
			sleep(5000);
			report.append("<td>Settings Page Opened</br>");
			UiObject searchEngine = new UiObject(
					new UiSelector().text("Search Engine"));
			if (searchEngine.exists()) {
				searchEngine.click();
				sleep(3000);
				new UiObject(new UiSelector().text("Bing")).click();
				report.append("Setting search engine to Bing</br>");
				getUiDevice().pressBack();
				if (omnibox.exists()) {
					omnibox.click();
					omnibox.setText("key");
					sleep(5000);
					getUiDevice().click(735, 1135);
					sleep(5000);
					if (urlClass.getText().contains("bing")) {
						report.append("Search Engine set to Bing </td>");
						testCaseSuccessMessage();
					} else {
						report.append("Bing not set as search engine</td>");
						testCaseFailureMessage();
					}
				} else {
					report.append("Could not type in omnibar</td>");
					testCaseFailureMessage();
				}
			} else {
				report.append("Search Engine option not found</td>");
				testCaseFailureMessage();
				getUiDevice().pressBack();
			}

		} catch (AssertionError ex) {
			report.append("<td>");
			AppendMessageAlone(ex);
			report.append("</br> Search Engine Check Failure</td>");
			testCaseFailureMessage();
		}

		// Open private browsing tab
		appendSerialNumber();
		System.out.println(" Private Browsing Tab Search");
		report.append("<td> Private Browsing Tab Search </td>");
		UiObject privateBrowsing = new UiObject(
				new UiSelector().description("privatebrowsing"));
		try {
			assertTrue(
					"Private Browsing button not found - No footer available",
					privateBrowsing.exists());
			sleep(5000);
			privateBrowsing.clickAndWaitForNewWindow();
			report.append("<td>Private Browsing tab opened</br>");
			if (omnibox.exists()) {
				omnibox.click();
				omnibox.setText(private_browsing_key);
				sleep(5000);
				getUiDevice().click(735, 1135);
				sleep(7000);
				report.append("Searched in Private Browsing tab </td>");
				testCaseSuccessMessage();
			} else {
				report.append("Could not type in omnibar</td>");
				testCaseFailureMessage();
			}
		} catch (AssertionError ex) {
			AppendMessageWithTags(ex);
		}

		// Private Browsing History Check
		appendSerialNumber();
		System.out.println("Private Browsing History Check");
		report.append("<td>Private Browsing History Check</td>");
		if (!privateBrowsing.exists()) {
			report.append("<td>Private Browsing Check Failed</td>");
			testCaseFailureMessage();
		} else {

			// Open menu
			openMenu();

			try {
				assertTrue("My Bookmarks page not found - ",
						myBookmarks.exists());
				myBookmarks.click();
				report.append("<td>My Bookmarks page opened</br>");
				sleep(30000);
				if (history.exists()) {
					history.click();
					report.append("Checking history section</br>");
					sleep(8000);
					UiObject private_key_class = new UiObject(new UiSelector()
							.className("android.widget.TextView").index(1));
					if (private_key_class.exists()) {
						String private_key = private_key_class.getText();
						if (private_key.contains(private_browsing_key)) {
							report.append("Private Browsing History saved!</td>");
							testCaseFailureMessage();
							getUiDevice().pressBack();
						} else {
							report.append("Private Browsing History not saved</td>");
							testCaseSuccessMessage();
							getUiDevice().pressBack();
						}
					} else {
						report.append("Could not check history </td>");
						testCaseFailureMessage();
						getUiDevice().pressBack();
					}
				} else {
					report.append("History section not found</td>");
					testCaseFailureMessage();
					getUiDevice().pressBack();
				}

			} catch (AssertionError ex) {
				report.append("<td>");
				AppendMessageAlone(ex);
				report.append("</br> Private Browsing Check Failed</td>");
				testCaseFailureMessage();
			}
		}

		// Open menu
		openMenu();

		// My Account Page
		sleep(4000);
		appendSerialNumber();
		System.out.println("Open My Account Page");
		report.append("<td>Open My Account Page</td>");
		UiObject myAccount = new UiObject(new UiSelector().text("My Account"));
		try {
			assertTrue("My Account Page not found", myAccount.exists());
			myAccount.clickAndWaitForNewWindow();
			sleep(6000);
			report.append("<td>My Account page opened successfully</td>");
			testCaseSuccessMessage();
		} catch (AssertionError ex) {
			AppendMessageWithTags(ex);
		}

		// Log out
		appendSerialNumber();
		System.out.println("Logging Out");
		report.append("<td>Logging Out : </br></br> i) Currency before Log Out : <br/></br> ii) Currency after Log Out : </td><td></br></br>");
		getCurrencyColorPoints();
		if (!badge.exists()) {
			report.append("Notifications - NULL");
		} else {
			get_notifications = badge.getText();
			notification = Integer.parseInt(get_notifications);
			report.append("Notifications - " + notification);
		}
		report.append("</br></br>");

		try {
			assertTrue("Menu not found - Log Out Fail", main_menu.exists());
			main_menu.click();
			sleep(3000);
			settings.click();
			sleep(5000);
			new UiObject(new UiSelector().text("Log Out of Browser"))
					.clickAndWaitForNewWindow();
			new UiObject(new UiSelector().text("Yes")).click();
			sleep(6000);
			getCurrencyDetails();
			report.append("</br>");
			if (greyCurrency.exists()) {
				testCaseSuccessMessage();
			} else {
				testCaseFailureMessage();
			}

		} catch (AssertionError ex) {
			AppendMessageAlone(ex);
			report.append("</td>");
			testCaseFailureMessage();
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
			appToLaunch = appViews.getChildByText(selector, "Gmail");
			assertNotNull("Gmail App not found", appToLaunch);
			if (appToLaunch != null)
				appToLaunch.clickAndWaitForNewWindow();
			report.append("<td></br></br> Gmail opened </br>");

			new UiObject(new UiSelector().description("Compose")).click();
			UiObject receipent = new UiObject(
					new UiSelector().description("To"));
			receipent.click();
			receipent.setText("abc@xyz.com");
			sleep(6000);
			UiObject emailSubject = new UiObject(
					new UiSelector().text("Subject"));
			emailSubject.click();
			emailSubject.setText("Browser Android " + appVersion
					+ " Automation Test Report");
			UiObject composeEmail = new UiObject(
					new UiSelector().text("Compose email"));
			String emailBody = "Hey\n\nPlease find below the Test Report of "
					+ appName
					+ " latest "
					+ appBuildType
					+ " build "
					+ appVersion
					+ " -\n\nhttp://abc.com/reports/browserTestReports/Browser_Android_v0.0.1_Development.html\n\nIt covers all the following use cases :\n\n[1] Launch and Walkthrough\n[2] Email signup\n[3] Currency color checks on partner visits through various means:\n i) Direct Visit\nii) Green highlighted search results page visit\niv) Omnibar store visit\n[4] Checking app shortcut on home screen \n[5] Relaunching app from background\n[6] Opening links form third party apps\n[7] Launching browser from notification panel\n[8] Check-in functionality\n[9] Bookmark functionality\n[11] Clear history check\n[12] Search Engine change check\n[13] Private Browsing History Check\n[14] Currency checks before and after Log Out\n[15] Email Broadcast\n\nThanks\nSmriti\n---\nThis is an automated email broadcast";
			composeEmail.setText(emailBody);
			new UiObject(new UiSelector().description("Send")).click();
			report.append("Email sent </td>");
			testCaseSuccessMessage();
		} catch (AssertionError ex) {
			AppendMessageWithTags(ex);
		}

		report.append("</table></br></br><b><center>&copy; 2014 </center></b></body></html>");
		if (testreport != null)
			testreport.run();

	}

	public void appendSerialNumber() {
		report.append("<tr><td>");
		serial_no++;
		report.append(serial_no);
		report.append("</td>");
	}

	public void launchBrowser() throws UiObjectNotFoundException {
		appendSerialNumber();
		System.out.println("Launch Browser");
		report.append("<td>Launch Browser</td>");
		getUiDevice().pressHome();
		allAppsButton = new UiObject(new UiSelector().description("Apps"));
		allAppsButton.click();

		appsTab = new UiObject(new UiSelector().text("Apps"));
		appsTab.click();
		appViews = new UiScrollable(new UiSelector().scrollable(true));
		appViews.setAsHorizontalList();
		selector = new UiSelector().className(android.widget.TextView.class
				.getName());

		try {
			appToLaunch = appViews.getChildByText(selector, "Browser");
			if (appToLaunch != null)
				appToLaunch.clickAndWaitForNewWindow();
			sleep(5000);
			report.append("<td>Browser Launched</td>");
			testCaseSuccessMessage();

		} catch (UiObjectNotFoundException e) {
			report.append("<td>App launch fail</td>");
			testCaseFailureMessage();
			System.exit(0);
		}
	}


	public void getCurrencyDetails() throws UiObjectNotFoundException {
		getCurrencyColorPoints();
		getNotificationCount();
	}

	public void getCurrencyColorPoints() throws UiObjectNotFoundException {

		try {
			assertTrue("Currency - NULL , ", currencyClass.exists());
			try {
				if (yellowCurrency.getContentDescription() != null) {
					System.out.println("Currency - Yellow");
					report.append("Currency - Yellow , ");
					currency_value = yellowCurrency.getText();
					points = Integer.parseInt(currency_value);
					report.append("Points - " + points + " , ");
				}

			} catch (Exception e) {
				System.err.println("\nCaught Exception: " + e.getMessage());
			}

			try {
				if (greyCurrency.getContentDescription() != null) {
					System.out.println("Currency - Grey");
					report.append("Currency - Grey , ");
					currency_value = greyCurrency.getText();
					points = Integer.parseInt(currency_value);
					report.append("Points - " + points + " , ");
				}

			} catch (Exception e) {
				System.err.println("\nCaught Exception: " + e.getMessage());
			}

			try {
				if (greenCurrency.getContentDescription() != null) {
					System.out.println("Currency - Green");
					report.append("Currency - Green , ");
					currency_value = greenCurrency.getText();
					points = Integer.parseInt(currency_value);
					report.append("Points - " + points + " , ");
				}

			} catch (Exception e) {
				System.err.println("\nCaught Exception: " + e.getMessage());
			}

		} catch (AssertionError ex) {
			AppendMessageAlone(ex);
			report.append("</td>");
			testCaseFailureMessage();
		}

	}

	public void getGreenCurrency() {
		if ((greenCurrency.exists()) && (result.contains("true"))) {
			testCaseSuccessMessage();
		} else {
			testCaseFailureMessage();
		}
	}

	public void getNotificationCount() throws UiObjectNotFoundException {

		if (!badge.exists()) {
			report.append("Notifications - NULL");
		} else {
			get_notifications = badge.getText();
			notification = Integer.parseInt(get_notifications);
			report.append("Notifications - " + notification);
		}
		report.append("</td>");
	}

	public void relaunchAppBackground() throws RemoteException,
			UiObjectNotFoundException {
		appendSerialNumber();
		System.out.println("Relaunch app in background");
		report.append("<td> Relaunch app from background </td>");
		getUiDevice().pressRecentApps();
		sleep(5000);
		appBackground = new UiObject(new UiSelector().description("Browser"));
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

	public void orbLaunch() throws UiObjectNotFoundException {

		appendSerialNumber();
		System.out.println("Launch orb menu");
		report.append("<td>Orb Menu Launch</td>");
		try {
			assertTrue("ORB not found - No footer available", orbmenu.exists());
			orbmenu.clickAndWaitForNewWindow(20);
			report.append("<td>Orb Menu opened successfully</td>");
			testCaseSuccessMessage();
		} catch (AssertionError ex) {
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

	public void launchBrowserPersistentSearch()
			throws UiObjectNotFoundException {

		appendSerialNumber();
		System.out.println(" Launch Browser from search tray");
		report.append("<td> Launch Browser from search tray </td>");
		try {
			getUiDevice().drag(88, 4, 170, 416, 1);
			assertNotNull("Browser launch fail", persistentSearch);
			persistentSearch.clickAndWaitForNewWindow();
			report.append("<td>Browser Launched from search tray</br>");
			sleep(3000); 
			if(new UiObject(new UiSelector().resourceId("com.example.browsertest.mobile:id/opaqueImage")).getBounds().contains(0,134,768,1184)) {
				report.append("Keyboard appeared instantly</td>");
				testCaseSuccessMessage();
			} else {
				report.append("Keyboard didn't appear</td>");
				testCaseFailureMessage();
			}
		} catch (AssertionError ex) {
			AppendMessageWithTags(ex);
		}
	}

	public void testCaseFailureMessage() {
		report.append("<td class=\"centered-cell\" bgcolor=\"#ff6347\">Failure</td></tr>");
		testcases_failed++;
	}

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

	public void checkCrash() {
		UiObject Crash = new UiObject(
				new UiSelector().text("Unfortunately, Browser has stopped."));
		if (Crash.exists()) {
			System.out.println("CRASH");
			report.append("</td>");
			testCaseFailureMessage();
			report.append("</table><h1 style=\"text-align:center;background-color:#ff6347;\">BROWSER CRASHED!!</h1></br><b><center>&copy; 2014</center></b></body></html>");
			if (testreport != null) {
				testreport.run();
			}
			System.exit(0);
		}
	}
}
