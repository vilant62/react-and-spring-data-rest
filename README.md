# React and Spring data Test assignment

## react-and-spring-data-rest

The application has a react frontend and a Spring Boot Rest API, packaged as a single module Maven application.
You can build the application running (`./mvnw clean verify`), that will generate a Spring Boot flat JAR in the target folder.
To start the application you can just run (`java -jar target/react-and-spring-data-rest-*.jar`), then you can call the API by using the following curl (shown with its output):

```bash

\$ curl -v -u greg:turnquist localhost:8080/api/employees/1
{
"firstName" : "Frodo",
"lastName" : "Baggins",
"description" : "ring bearer",
"manager" : {
"name" : "greg",
"roles" : [ "ROLE_MANAGER" ]
},
"\_links" : {
"self" : {
"href" : "http://localhost:8080/api/employees/1"
        }
    }
}

```

To see the frontend, navigate to <http://localhost:8080>. You are immediately redirected to a login form. Log in as `greg/turnquist`

## Cloud-DevOps assignment

As the expert of the team in cloud and automation, you are supposed to lead the way architecting
and deploying the application to the cloud. For the purpose of this assignment, we have provided you with an attachment that contains a simple application with the mentioned components plus an in-memory database (it can remain in memory for the purpose of the assignment). The team together with the product owner and the scrum master, has defined the following user stories:

- **As a DevOps engineer, I want to have a CI/CD pipeline for my application:**

  - The pipeline must build and test the application code base.
  - The pipeline must build and push a Docker container ready to use.
  - The pipeline must deploy the application across different environments on the target infrastructure.
  - **Bonus point:** Separate the backend and the frontend in different pipelines and containers

- **As a DevOps engineer, I want to have a pipeline to deploy the required infrastructure for my application:**

  - The infrastructure must be created on the cloud, for the purpose of the assignment any public cloud can be used.
  - The deployment pipeline must use infrastructure as code (Cloud Formation, Cloud Deployment Manager, Azure Resource Manager or Terraform).
  - The delivered infrastructure must be monitored and audited.
  - The delivered infrastructure must allow multiple personal accounts.
  - For the purpose of the assignment, you will define the cloud architecture that you see fit, document it and explain the resources created and choices made.

## Solution 1 Azure DevOps Pipeline for pull-requests

As a solution the pull-requests pipelin has created to run unittests. The pipeline [ci.azure-pipelines.uml](.azure-pipelines/ci.azure-pipelines.yml) is triggered for new pull-requests and for new commits in pull-requests. It contains one stage `tests` and run `mvn test` command in repository.

The second pipeline [cd.azure-pipelines.yml](.azure-pipelines/ci.azure-pipelines.yml) runs for the `main` branch and contains three stages `build`, `provisioning` and `destroy`. It triggers on new commits in the `main` branch. On the `build` stage the new Docker container with application builded and pushed to the repository. The stage `provisioning` create an Azure resource group and perform the Azure Bicep file deployment in the resource group.

## Solution 2 Azure Bicep to deploy Container application in the Azure Cloud

The deployment configuration utilizes Azure Bicep, a domain-specific language for declaratively deploying Azure resources. The [main.bicep](./bicep/main.bicep) file, defines all the necessary resources required to run the application. This approach allows a more manageable and reproducible infrastructure-as-code setup.

## How to copy and setup project
