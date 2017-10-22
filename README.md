# Zuul Reverse Proxy Demo

Zuul Spring.io Docs: http://cloud.spring.io/spring-cloud-netflix/single/spring-cloud-netflix.html#_router_and_filter_zuul

## Overview
This demo will cover how to get up and running with Zuul as a reverse proxy.  It will cover the different types of Zuul filters,
and how to leverage them to perform actions during the proxying process. 

## Assumed Knowledge
1) Spring Boot
2) Maven

## What is Zuul?
Zuul is a JVM based router and server side load balancer by Netflix.  It is a framework that allows you to create Filters
and specify at which point in the request routing and under what conditions to perform actions. 
 
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

![Zuul Life Cycle](./zuul-life-cycle.png)