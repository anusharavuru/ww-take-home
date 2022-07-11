### Under src/test/java,
1. com.Pages contain all the code related to pages of the application
2. com.Test have the test script asked to be implemented
3. com.Util have all the common and Helper methods. Also data is stored in Data.properties under this.

## Added chrome driver executable for MAC 64bit for chrome version 103 which is in 'src/test/resources/DriverExecutables'.Also updated OS as 'mac' in Data.properties file for WW to execute

## Most of the classes in Util can be separated as a separate framework and be reused in multiple projects
## Used basic TestNG reporting,better reporting libraries can be used and customized
## Used basic POM framework and can be customized and enhanced
## Used Log4j for storing logs and Properties file for test data as there is minimum data needed for this script. Different source of data files can be used based on the requirement
