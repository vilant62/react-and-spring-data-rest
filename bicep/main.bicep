targetScope = 'resourceGroup'

param image string
param imageTag string
param appName string
param location string = 'northeurope'

var containerName = 'springapp'
var containerPort = 8080

resource farmapp 'Microsoft.App/managedEnvironments@2024-03-01' = {
  name: '${appName}-serverfarm'
  location: location
  properties: {
    workloadProfiles: [
      { name: 'Consumption', workloadProfileType: 'Consumption' }
    ]
  }
}

resource containerapp 'Microsoft.App/containerApps@2024-03-01' = {
  name: appName
  location: location
  properties: {
    managedEnvironmentId: farmapp.id
    configuration: {
      ingress: {
        external: true
        targetPort: containerPort
        allowInsecure: false
        transport: 'auto'
      }
    }
    template: {
      containers: [
        {
          name: containerName
          image: '${image}:${imageTag}'
        }
      ]
      scale: {
        minReplicas: 0
        maxReplicas: 1
      }
    }
  }
}

output containerAppUrl string = 'https://${containerapp.properties.configuration.ingress.fqdn}'
