job('ejemplo2-job-DSL') {
	description('job DSL')
  	scm{
      git('https://github.com/FlorcitaHnatiuk/jenkins.job.parametrizado.git', 'main') { node ->
        node / gitConfigName('Florcita Hnatiuk')
        node / gitConfigEmail('flopi.hnatiuk@gmail.com')
      }
    }
  	parameters {
    	stringParam('nombre', defaultValue = 'Florencia', description = 'Parametro de cadena para Job DSL')
    	choiceParam('planeta', ['Mercurio', 'Venus', 'Tierra', 'Marte', 'Júpiter', 'Saturno', 'Urano', 'Neptuno'])
  		booleanParam('agente', false)
  		}
  	triggers {
    	cron('H/7 * * * *')
  		}
  	steps {
  		shell("bash jobscript.sh")
  	}
  	publishers {
      	mailer('flopi.hnatiuk@gmail.com', true, true)
      	slackNotifier {
      		notifyAborted(true)
          	notifyEveryFailure(true)
          	notifyNotBuilt(false)
          	notifyUnstable(false)
          	notifyBackToNormal(true)
          	notifySuccess(false)
          	notifyRepeatedFailure(false)
          	startNotification(false)
          	includeTestSummary(false)
          	includeCustomMessage(false)
          	customMessage(null)
          	sendAs(null)
          	commitInfoChoice('NONE')
          	teamDomain(null)
          	authToken(null)
      	}
  	}
}
