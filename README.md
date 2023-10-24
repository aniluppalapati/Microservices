# Microservices

📈 Limits-Service: Our financial control hub. This microservice establishes a connection with the Spring Config Server, acting as the bridge to our Git repository. This synchronization ensures that properties are effortlessly fetched and shared with the Limits-Service.

💱 Currency-Exchange: Our data maven. This service interfaces with an H2 in-memory database to retrieve critical currency details.

🔄 Currency-Conversion: The agile performer in our ensemble, connecting to Currency-Exchange. It orchestrates the data waltz, executing necessary operations using RestTemplate. We later upgraded to the elegant Feign Client to streamline the act.

📡 Service Registration:

We orchestrated a grand symphony with the services, unified in a registry known as the Eureka Server. This registry allows the services to discover each other seamlessly.
⚖️ Scaling Up and Down:

Scaling is an art form. We dynamically fine-tuned our resources, ensuring the perfect balance between demand and supply.

🔀 Load Balancer:

Our maestro, the load balancer, conducted the symphony of traffic. It ensured that each service played its part harmoniously.

🌐 API Gateway:

The guardian at the grand entrance. We created a central API gateway that served as the unifying entry point, ensuring security and streamlined access to our services.

🪶 Resilience4j:

Our safety net during turbulent times. With fallback responses and Circuit Breaker patterns, Resilience4j was our guardian angel, providing protection during service hiccups.
