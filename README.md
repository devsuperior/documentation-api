# Documentação de API com Swagger e OpenAPI 3.0 no Spring Boot com Java
>  *Criar uma documentação para o projeto Dsmovie usando Swagger e OpenAPI 3.0*

### Passo 1: Dependência maven

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.1.0</version>
</dependency>
```
Referência: https://springdoc.org/

### Passo 2: Classe de configuração

- Incluir a classe OpenAPIConfig no pacote config:

```java
@OpenAPIDefinition
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI dsmovieAPI() {
        return new OpenAPI()
           .info(new Info()
           .title("DSMovie API")
           .description("DSMovie Reference Project")
           .version("v0.0.1")
           .license(new License()
           .name("Apache 2.0")
           .url("https://github.com/devsuperior/dsmovie-ref")));
    }
}
```
### Passo 3: Acessar documentação

- Link: http://localhost:8080/swagger-ui.html

## Tópicos avançados - Recursos no Swagger

### Passo 1: Personalizar o swagger

- Anotações nos recursos (controllers)

```java
@Tag(name = "Movies", description = "Controller for Movie")
public class MovieController {
```

- Anotações nos endpoints REST

```java
@Operation(
    description = "Create a new movie",
    summary = "Create a new movie",
    responses = {
         @ApiResponse(description = "Created", responseCode = "201"),
         @ApiResponse(description = "Bad Request", responseCode = "400"),
         @ApiResponse(description = "Unauthorized", responseCode = "401"),
         @ApiResponse(description = "Forbidden", responseCode = "403"),
         @ApiResponse(description = "Unprocessable Entity", responseCode = "422")
    }
)
@PreAuthorize("hasRole('ROLE_ADMIN')")
@PostMapping
public ResponseEntity<MovieDTO> insert(@RequestBody MovieDTO dto) {
```
```java
@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
public MovieDTO findById(@PathVariable Long id) {
```

- Anotações model

```java
public class MovieDTO {

	@Schema(description = "Database generated movie ID")
	private Long id;
	
	@Schema(description = "Movie title")
	private String title;
```
## Tópicos avançados: 

### Configurações com Spring Security

- Incluir anotação @SecurityScheme na classe de configuração

```java
@OpenAPIDefinition
@Configuration
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer")
public class OpenApiConfig {

    @Bean
    public OpenAPI dsmovieAPI() {
        return new OpenAPI()
           .info(new Info()
           .title("DSMovie API")
           .description("DSMovie Reference Project")
           .version("v0.0.1")
           .license(new License()
           .name("Apache 2.0")
           .url("https://github.com/devsuperior/dsmovie-ref")));
    }
}
```
- Inclcuir anotação @SecurityRequirement nos endpoints protegidos
```java
@SecurityRequirement(name = "bearerAuth")
@DeleteMapping(value = "/{id}")
public ResponseEntity<MovieDTO> delete(@PathVariable Long id) {
```
### Gerar especificação OpenAPI da API

- Specify the path of the OpenAPI documentation
```xml
springdoc.api-docs.path=/api-docs
```
- Acessar especificação: http://localhost:8080/api-docs
  
