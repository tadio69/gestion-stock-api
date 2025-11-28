# CommandesClientApi

All URIs are relative to *http://localhost:8081*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**delete7**](CommandesClientApi.md#delete7) | **DELETE** /gestionstock/v1/commandes-client/delete/{id} | Supprimer une commande client par son ID |
| [**findAll7**](CommandesClientApi.md#findAll7) | **GET** /gestionstock/v1/commandes-client/all | Renvoyer la liste des commandes client |
| [**findByCode2**](CommandesClientApi.md#findByCode2) | **GET** /gestionstock/v1/commandes-client/code/{code} | Rechercher une commande client par son code |
| [**findById7**](CommandesClientApi.md#findById7) | **GET** /gestionstock/v1/commandes-client/{id} | Rechercher une commande client par son ID |
| [**save6**](CommandesClientApi.md#save6) | **POST** /gestionstock/v1/commandes-client/create | Enregistrer ou modifier une commande client |


<a id="delete7"></a>
# **delete7**
> delete7(id)

Supprimer une commande client par son ID

Cette méthode permet de supprimer une commande client par son identifiant

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CommandesClientApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8081");

    CommandesClientApi apiInstance = new CommandesClientApi(defaultClient);
    Integer id = 56; // Integer | 
    try {
      apiInstance.delete7(id);
    } catch (ApiException e) {
      System.err.println("Exception when calling CommandesClientApi#delete7");
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
| **200** | Commande client supprimée avec succès |  -  |

<a id="findAll7"></a>
# **findAll7**
> List&lt;CommandeClientDto&gt; findAll7()

Renvoyer la liste des commandes client

Cette méthode permet de retourner la liste des commandes client enregistrées

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CommandesClientApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8081");

    CommandesClientApi apiInstance = new CommandesClientApi(defaultClient);
    try {
      List<CommandeClientDto> result = apiInstance.findAll7();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CommandesClientApi#findAll7");
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

[**List&lt;CommandeClientDto&gt;**](CommandeClientDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Liste des commandes client retournée avec succès |  -  |

<a id="findByCode2"></a>
# **findByCode2**
> CommandeClientDto findByCode2(code)

Rechercher une commande client par son code

Cette méthode permet de chercher une commande client par son code

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CommandesClientApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8081");

    CommandesClientApi apiInstance = new CommandesClientApi(defaultClient);
    String code = "code_example"; // String | 
    try {
      CommandeClientDto result = apiInstance.findByCode2(code);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CommandesClientApi#findByCode2");
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

[**CommandeClientDto**](CommandeClientDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Commande client trouvée avec succès |  -  |
| **404** | Aucune commande client trouvée avec ce code |  -  |

<a id="findById7"></a>
# **findById7**
> CommandeClientDto findById7(id)

Rechercher une commande client par son ID

Cette méthode permet de chercher une commande client pas son identifiant

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CommandesClientApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8081");

    CommandesClientApi apiInstance = new CommandesClientApi(defaultClient);
    Integer id = 56; // Integer | 
    try {
      CommandeClientDto result = apiInstance.findById7(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CommandesClientApi#findById7");
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

[**CommandeClientDto**](CommandeClientDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **404** | Aucune commande client trouvée avec cet identifiant |  -  |
| **200** | Commande client trouvée avec succès |  -  |

<a id="save6"></a>
# **save6**
> CommandeClientDto save6(commandeClientDto)

Enregistrer ou modifier une commande client

Cette méthode permet d&#39;enregistrer ou modifier une commande client

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CommandesClientApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8081");

    CommandesClientApi apiInstance = new CommandesClientApi(defaultClient);
    CommandeClientDto commandeClientDto = new CommandeClientDto(); // CommandeClientDto | 
    try {
      CommandeClientDto result = apiInstance.save6(commandeClientDto);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CommandesClientApi#save6");
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
| **commandeClientDto** | [**CommandeClientDto**](CommandeClientDto.md)|  | |

### Return type

[**CommandeClientDto**](CommandeClientDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Commande client créée ou modifiée avec succès |  -  |
| **400** | Commande client non valide |  -  |

