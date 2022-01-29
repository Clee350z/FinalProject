# Honeycomb Hearts Final Project
## Hiking Trail App

##Team Members
DBA - Ebony Johnson
Scrum Master - Daniel Faulkner
Git Owner - Christopher Lee
CSS: Lead - Ryan Majors
      Additions: ALL
Backend - All

##Overview

### REST applying

### HTML/JavaScript
This project includes HTML/JavaScript functionality. It uses the HTML to create divs & the Script to dynamically create forms & tables to display each anime.

## Angular Front End

This project includes Angular functionality including all CRUD functionality to allow code reusability for HTML and structured components for maintainability.

### REST API Reference
|Return Type                | HTTP Method | URI                                                                | Request Body           | Purpose  |
|---------------------------|-------------|--------------------------------------------------------------------|------------------------|----------|
| List \<User\>             | GET         | /api/users                                                         |                        | List     |
| User                      | GET         | /api/users/{userId}                                                |                        | Retrieve |
| User                      | POST        | /api/users/{userId}                                                | User JSON              | Create   |
| Void                      | DELETE      | /api/users/{userId}                                                |                        | Delete   |
| List \<Address\>          | GET         | /api/addresses                                                     |                        | List     |
| Address                   | GET         | /api/addresses/{addressId}                                         |                        | Retrieve |
| Address                   | POST        | /api/addresses                                                     | Address JSON           | Create   |
| List \<Trail\>            | GET         | /api/trails                                                        |                        | List     |
| Trail                     | GET         | /api/trails/{trailId}                                              |                        | Retrieve |
| Trail                     | POST        | /api/trails                                                        | Trail JSON             | Create   |
| Trail                     | PUT         | /api/trails/{trailId}                                              | Trail JSON             | Update   |
| Void                      | DELETE      | /api/trails/{trailId}                                              |                        | Delete   |
| List \<Condition\>        | GET         | /api/trails/conditions                                             |                        | List     |
| List \<Difficulty\>       | GET         | /api/trails/difficulties                                           |                        | List     |
| List \<TrailComment\>     | GET         | /api/trails/comments                                               |                        | List     |
| TrailComment              | GET         | /api/trails/{trailId}/comments/{commentId}                         |                        | Retrieve |
| TrailComment              | POST        | /api/trails/{trailId}/comments                                     | TrailComment JSON      | Create   |
| TrailComment              | PUT         | /api/trails/{trailId}/comments/{commentId}                         | TrailComment JSON      | Update   |
| Void                      | DELETE      | /api/trails/{trailId}/comments/{commentId}                         |                        | Delete   |
| List \<GroupHike\>        | GET         | /api/trails/grouphikes                                             |                        | List     |
| GroupHike                 | GET         | /api/trails/{trailId}/grouphikes/{groupHikeId}                     |                        | Retrieve |
| GroupHike                 | POST        | /api/trails/{trailId}/grouphikes                                   | GroupHike JSON         | Create   |
| GroupHike                 | PUT         | /api/trails/{trailId}/grouphikes/{groupHikeId}                     | GroupHike JSON         | Update   |
| Void                      | DELETE      | /api/trails/{trailId}/grouphikes/{groupHikeId}                     |                        | Delete   |
| List \<GroupHikeComment\> | GET         | /api/trails/grouphikes/{groupHikeId}/comments                      |                        | List     |
| GroupHikeComment          | GET         | /api/trails/grouphikes/{groupHikeId}/comments/{commentId}          |                        | Retrieve |
| GroupHikeComment          | POST        | /api/trails/grouphikes/{groupHikeId}/comments                      | GroupHikeComment JSON  | Create   |
| GroupHikeComment          | PUT         | /api/trails/grouphikes/{groupHikeId}/comments/{commentId}          | GroupHikeComment JSON  | Update   |
| Void                      | DELETE      | /api/trails/grouphikes/{groupHikeId}/comments/{commentId}          |                        | Delete   |
| List \<HikeReport\>       | GET         | /api/trails/{trailId}/hikereports                                  |                        | List     |
| HikeReport                | GET         | /api/trails/{trailId}/hikereports/{reportId}                       |                        | Retrieve |
| HikeReport                | POST        | /api/trails/{trailId}/hikereports                                  | HikeReport JSON        | Create   |
| HikeReport                | PUT         | /api/trails/{trailId}/hikereports/{reportId}                       | HikeReport JSON        | Update   |
| Void                      | DELETE      | /api/trails/{trailId}/hikereports/{reportId}                       |                        | Delete   |
| List \<HikeReport\>       | GET         | /api/trails/{trailId}/hikereports/{reportId}/comments              |                        | List     |
| HikeReportComment         | GET         | /api/trails/{trailId}/hikereports/{reportId}/comments/{commentId}  |                        | Retrieve |
| HikeReportComment         | POST        | /api/trails/{trailId}/hikereports/{reportId}/comments              | HikeReportComment JSON | Create   |
| HikeReportComment         | PUT         | /api/trails/{trailId}/hikereports/{reportId}/comments/{commentId}  | HikeReportComment JSON | Update   |
| Void                      | DELETE      | /api/trails/{trailId}/hikereports/{reportId}/comments/{commentId}  |                        | Delete   |
| List \<HikeReport\>       | GET         | /api/trails/{trailId}/hikereports/{reportId}/photos                |                        | List     |
| HikeReportComment         | GET         | /api/trails/{trailId}/hikereports/{reportId}/photos/{photoId}      |                        | Retrieve |
| HikeReportComment         | POST        | /api/trails/{trailId}/hikereports/{reportId}/photos                | HikePhoto JSON         | Create   |
| HikeReportComment         | PUT         | /api/trails/{trailId}/hikereports/{reportId}/photos/{photoId}      | HikePhoto JSON         | Update   |
| Void                      | DELETE      | /api/trails/{trailId}/hikereports/{reportId}/photos/{photoId}      |                        | Delete   |


##Technologies used
*Slack
*JPA
*MySQL
*HTML/CSS
*FIGMA.COM
*SpringBoot

##Lessons learned

Personal goals
