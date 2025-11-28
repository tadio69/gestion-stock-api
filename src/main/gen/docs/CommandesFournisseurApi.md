# CommandesFournisseurApi

All URIs are relative to *http://localhost:8081*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**delete6**](CommandesFournisseurApi.md#delete6) | **DELETE** /gestionstock/v1/commandes-fournisseur/delete/{id} | Supprimer une commande fournisseur par son ID |
| [**findAll6**](CommandesFournisseurApi.md#findAll6) | **GET** /gestionstock/v1/commandes-fournisseur/all | Renvoyer la liste des commandes fournisseur |
| [**findByCode1**](CommandesFournisseurApi.md#findByCode1) | **GET** /gestionstock/v1/commandes-fournisseur/code/{code} | Rechercher une commande fournisseur par son code |
| [**findById6**](CommandesFournisseurApi.md#findById6) | **GET** /gestionstock/v1/commandes-fournisseur/{id} | Rechercher une commande fournisseur par son ID |
| [**save5**](CommandesFournisseurApi.md#save5) | **POST** /gestionstock/v1/commandes-fournisseur/create | Enregistrer ou modifier une commande fournisseur |


<a id="delete6"></a>
# **delete6**
> delete6(id)

Supprimer une commande fournisseur par son ID

Cette méthode permet de supprimer une commande fournisseur par son identifiant

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CommandesFournisseurApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8081");

    CommandesFournisseurApi apiInstance = new CommandesFournisseurApi(defaultClient);
    Integer id = 56; // Integer | 
    try {
      apiInstance.delete6(id);
    } catch (ApiException e) {
      System.err.println("Exception when calling CommandesFournisseurApi#delete6");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Integer**|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Commande fournisseur supprimée avec succès |  -  |

<a id="findAll6"></a>
# **findAll6**
> List&lt;CommandeFournisseurDto&gt; findAll6()

Renvoyer la liste des commandes fournisseur

Cette méthode permet de retourner les commandes fournisseur enregistrées

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CommandesFournisseurApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8081");

    CommandesFournisseurApi apiInstance = new CommandesFournisseurApi(defaultClient);
    try {
      List<CommandeFournisseurDto> result = apiInstance.findAll6();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CommandesFournisseurApi#findAll6");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;CommandeFournisseurDto&gt;**](CommandeFournisseurDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Liste des commandes fournisseur retournée avec succès |  -  |

<a id="findByCode1"></a>
# **findByCode1**
> CommandeFournisseurDto findByCode1(code)

Rechercher une commande fournisseur par son code

Cette méthode permet de chercher la commande fournisseur par son code

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CommandesFournisseurApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8081");

    CommandesFournisseurApi apiInstance = new CommandesFournisseurApi(defaultClient);
    String code = "code_example"; // String | 
    try {
      CommandeFournisseurDto result = apiInstance.findByCode1(code);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CommandesFournisseurApi#findByCode1");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **code** | **String**|  | |

### Return type

[**CommandeFournisseurDto**](CommandeFournisseurDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Commande fournisseur trouvée avec succès |  -  |
| **404** | Aucune commande fournisseur trouvée avec ce code |  -  |

<a id="findById6"></a>
# **findById6**
> CommandeFournisseurDto findById6(id)

Rechercher une commande fournisseur par son ID

Cette méthode permet de chercher une commande fournisseur par son identifiant

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CommandesFournisseurApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8081");

    CommandesFournisseurApi apiInstance = new CommandesFournisseurApi(defaultClient);
    Integer id = 56; // Integer | 
    try {
      CommandeFournisseurDto result = apiInstance.findById6(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CommandesFournisseurApi#findById6");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Integer**|  | |

### Return type

[**CommandeFournisseurDto**](CommandeFournisseurDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Commande fournisseur trouvée avec succès |  -  |
| **404** | Aucune commande fournisseur trouvée avec cet identifiant |  -  |

<a id="save5"></a>
# **save5**
> CommandeFournisseurDto save5(commandeFournisseurDto)

Enregistrer ou modifier une commande fournisseur

Cette méthode permet de créer ou de modifier une commande fournisseur

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CommandesFournisseurApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8081");

    CommandesFournisseurApi apiInstance = new CommandesFournisseurApi(defaultClient);
    CommandeFournisseurDto commandeFournisseurDto = new CommandeFournisseurDto(); // CommandeFournisseurDto | 
    try {
      CommandeFournisseurDto result = apiInstance.save5(commandeFournisseurDto);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CommandesFournisseurApi#save5");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **commandeFournisseurDto** | [**CommandeFournisseurDto**](CommandeFournisseurDto.md)|  | |

### Return type

[**CommandeFournisseurDto**](CommandeFournisseurDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Commande fournisseur créée ou modifiée avec succès |  -  |
| **400** | Commande fournisseur non valide |  -  |

