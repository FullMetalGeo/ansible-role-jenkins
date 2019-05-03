//
// Configure Tool - Maven
//
// Installs a Maven installation named "maven-3" if one is not already found.
// References:
// * https://github.com/batmat/jez/blob/master/jenkins-master/init_scripts/add_maven_auto_installer.groovy
// * https://wiki.jenkins-ci.org/display/JENKINS/Add+a+Maven+Installation%2C+Tool+Installation%2C+Modify+System+Config
// * https://github.com/glenjamin/jenkins-groovy-examples/blob/master/README.md


// These are the basic imports that Jenkin's interactive script console
// automatically includes.
import jenkins.*;
import jenkins.model.*;
import hudson.*;
import hudson.model.*;

def install_maven(String mavenVersion) {
  println("Checking Maven installations...")
  println("Maven Version: " + mavenVersion)
  mavenName = "maven-" + mavenVersion

  // Grab the Maven "task" (which is the plugin handle).
  mavenPlugin = Jenkins.instance.getExtensionList(hudson.tasks.Maven.DescriptorImpl.class)[0]

  // Check for a matching installation.
  maven3Install = mavenPlugin.installations.find {
      install -> install.name.equals(mavenName)
  }

  // If no match was found, add an installation.
  if(maven3Install == null) {
      println("No Maven install found. Adding...")

      newMavenInstall = new hudson.tasks.Maven.MavenInstallation(mavenName, null,
          [new hudson.tools.InstallSourceProperty([new hudson.tasks.Maven.MavenInstaller(mavenVersion)])]
      )

      mavenPlugin.installations += newMavenInstall
      mavenPlugin.save()

      println("Maven install added.")
  } else {
      println("Maven install found. Done.")
  }
}
def versions = ["2.2.1", "3.3.9", "3.5.0", "3.5.2", "3.5.4", "3.6.0", "3.6.1"]
for (mavenVersion in versions) {
  this.&install_maven(mavenVersion)
}

