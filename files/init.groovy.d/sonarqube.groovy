import jenkins.model.*
import hudson.plugins.sonar.*
import hudson.tools.*

def inst = Jenkins.getInstance()

def desc = inst.getDescriptor("hudson.plugins.sonar.SonarRunnerInstallation")

def installer = new SonarRunnerInstaller("[3.2.0.1227]")
def prop = new InstallSourceProperty([installer])
def sinst = new SonarRunnerInstallation("[sonar_3.2]", "[]", [prop])
desc.setInstallations(sinst)

desc.save()
