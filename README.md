## Installing Java and Node

1. Installing Node :
   1. Go to https://nodejs.org/en
   2. Select the 20.11.1
   3. Download it
   4. Install it using the exe (the repertory doesn't matter)
      1. Follow the step
      2. Install all components
      3. Don't install chocolatey after
   5. Check your installation: Open a cmd and run 'node -v' if the version appears it's
      OK, if not just restart your computer
   6. Run **npm install -g @angular/cli**.
2. Installing Java (using IntelliJ)
   1. Go to File --> Project Structure --> Project
   2. If you already have the **Eclipse Temurin (AdoptOpenJDK HotSpot) 17.0.10** SDK =>
      Select IT
   3. IF NOT, follow these steps:
      1. Click on the SDK drop down list then Add SDK
      2. Select Download SDK
      3. Select the version (17)
      4. Select the vendor (Eclipse Temurin (AdoptOpenJDK HotSpot) 17.0.10)
      5. Leave the default location
      6. Click on Download
3. Installing Java (manually)
   1. Go to : https://adoptium.net/fr/temurin/releases/?version=17&os=any
   2. Select your OS
   3. Select the version you want
   4. Select the installer you want
      1. ZIP --> Unzip
      2. MSI --> Run .msi --> Select the component and folder from the installation
   5. In both cases --> Go to IntelliJ --> File --> Project Structure --> Project -->
      Click on SDK --> Then Add SDK --> JDK --> Select the folder unzip

## Running the application

1. Clone the project from this url : https://github.com/BenjaminPetit05/DeviceMonitor.git
   1. The directory doesn't matter

### Run the backend

1. Open the cloned project in IntelliJ
2. Go to File --> Settings --> Build, Execution, Deployment --> Build Tools --> Maven -->
   Select **Maven home path** : Select Bundled (Maven 3) or Maven Wrapper
3. Enable lombok annotation processing
4. Right click on **DeviceMonitoringApplication** --> Run '
   DeviceMonitoringApplication ...'.
4. Wait for the application message : DeviceMonitoringApplication started in 3.288
   seconds (process running for 3.794)
5. **TIPS :** If trouble to detect the project in java :
   6. Set up the correct SDK
   7. Delete the .idea --> Close project --> And Re open it 
5. The backend is running

### Running the frontend

1. Go to **src/main/webapp/monitoring**.
2. Open a cmd or git bash
3. Run the command : **ng serve**.
4. Wait for the message : Local: http://localhost:4200/
5. Open the previous url in your navigator (tests were made on Google Chrome with the duck
   duck go extension and without)
6. The front is running

### Start monitoring

1. At this point your back and front are running fine.

2. The home page of the application shows nothing more than :

   1. The title: Device Monitoring 🖥
   2. Three buttons:
      1. Start monitoring
      2. Stop monitoring
      3. Refresh

3. Click Start Monitoring to start the monitoring process

   1. This will trigger the front-end and back-end to send data through the simulated
      devices.

   2. Wait until you have at least all three devices from each back and front end on the
      home page. You will see the name of the device and the number of messages sent.

   3. The home page will automatically refresh every 10 seconds.

   4. **TIPS**: If you want to generate more messages you can simply modify the following
      code:

      1. Back : MonitoringController --> expectedTime += 5000L; (~line 52) : This line
         triggers the device to send data every 5s.

      2. Front : MonitoringComponent --> this.intervalForSendingData [...] }, 10000) (~
         line 44-46) : This line triggers the front to send data every 10s.

      3. The database used is a H2 in memory, so if you stop this application the data
         will disappear. This can be changed by changing the application.properties in the
         Java application

         1. To change

            1. ```
               spring.datasource.url=jdbc:h2:mem:device-monitoring
               ```

         2. TO :

            1. ```
               spring.datasource.url=jdbc:h2:file:C:/Development/Projects/H2_db/devicemonitoring
               ```

            2. Use the folder of your choice

   5. When you have enough data, just stop monitoring

   6. Enjoy this easy monitoring application