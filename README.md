# ubicarpark-api

After statup a swagger documentation will be available at: http://localhost:8080/api/v1/swagger-ui.html

Notes:

- I have had a lot of discussion in the last years regarding the use of interfaces for services + "impl" classes. While I was always using them in the past for every service and dao class I have seen many projects that do not use them anymore. Good mocking frameworks remove the need to use these interfaces for testing purposes and I have seen rare cases where service classes have indeed been swapped out for other service classes justifying the code and files bloat many interfaces cause. So following the "YAGNI" principle I tend to leave them out nowadays but am not dogmatic with regards to that and would adapt to whatever is practised.
- I'm using a mix of synchronized methods and optimistic database locking to prevent race-conditions that could cause an invalid state. The optimistic locking is achieved by using a "version" field in the database. In this implementation it should for example not be possible for capacity to be freed up twice when a request to do so is incoming twice. In such a rare event an exception would be thrown. In a productive implementation this exception could be caught to retry the respective action.
