# Zuul Reverse Proxy Demo

Zuul Spring.io Docs: http://cloud.spring.io/spring-cloud-netflix/single/spring-cloud-netflix.html#_router_and_filter_zuul

## Overview
This demo will cover how to get up and running with Zuul as a reverse proxy.  It will cover the different types of Zuul filters,
and how to leverage them to perform actions during the proxying process. 

## Assumed Knowledge
1) Spring Boot
2) Maven

## What is Zuul?
Zuul is a JVM based router and server side load balancer by Netflix.  It easily allows you to extend it by creating and 
registering Filters.  These filters hold the logic on when they should be applied, under what conditions they should be applied,
and what actions they should run.  These can be set to run in several points of the routing process. 

### What can Zuul do?

Netflix uses Zuul for the following:

- Authentication
- Insights
- Stress Testing
- Canary Testing
- Dynamic Routing
- Service Migration
- Load Shedding
- Security
- Static Response handling
- Active/Active traffic management

## Filters
Filters are composed of 4 key properties.

#### Types
There are 4 types of filters in Zuul, that are all designed to serve a particular purpose.

- PRE: Will execute before the request has been routed.  These are useful for actions such as
adding an API token or other pieces of sensitive information.
- ROUTING: Handles the routing of the request through the use of Apache HttpClient or Ribbon.  Can be used to change the destination host, etc. 
- POST:  Executes after the request has been routed, but before it has been returned to the client.  Useful for statistics and metrics
gathering, and adding headers to the response. 
- ERROR: Executes if there is an exception in any other the other filters.

#### Execution Order
An integer value defining when the filter should be ran.  Runs from lowest to highest and is calculated across filter types.

#### Run Criteria
A set of criteria that determines if the filter should be executed.  The outcome of this will determine if the action should
be ran.

#### Action
What you would like to have performed if the conditions in the run criteria are true.

## Zuul Lifecycle
![Zuul Lifecycle](./zuul-life-cycle.png)