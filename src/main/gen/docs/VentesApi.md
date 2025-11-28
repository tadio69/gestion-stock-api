# VentesApi

All URIs are relative to *http://localhost:8081*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**delete**](VentesApi.md#delete) | **DELETE** /gestionstock/v1/ventes/delete/{id} | Supprimer une vente par son ID |
| [**findAll**](VentesApi.md#findAll) | **GET** /gestionstock/v1/ventes/all | Renvoyer la liste des ventes |
| [**findByCode**](VentesApi.md#findByCode) | **GET** /gestionstock/v1/ventes/code/{code} | Rechercher une vente par son code |
| [**findById**](VentesApi.md#findById) | **GET** /gestionstock/v1/ventes/{id} | Rechercher une vente par son ID |
| [**save**](VentesApi.md#save) | **POST** /gestionstock/v1/ventes/create | Enregistrer ou modifier une vente |


<a id="delete"></a>
# **delete**
> delete(id)

Supprimer une vente par son ID

Cette méthode permet de supprimer une vente par identifiant

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.VentesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8081");

    VentesApi apiInstance = new VentesApi(defaultClient);
    Integer id = 56; // Integer | 
    try {
      apiInstance.delete(id);
    } catch (ApiException e) {
      System.err.println("Exception when calling VentesApi#delete");
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
| **200** | Vente supprimée avec succès |  -  |

<a id="findAll"></a>
# **findAll**
> List&lt;VenteDto&gt; findAll()

Renvoyer la liste des ventes

Cette méthode permet de retourner la liste des ventes enregistrées

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.VentesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8081");

    VentesApi apiInstance = new VentesApi(defaultClient);
    try {
      List<VenteDto> result = apiInstance.findAll();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling VentesApi#findAll");
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

[**List&lt;VenteDto&gt;**](VenteDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Liste des ventes retournée avec succès |  -  |

<a id="findByCode"></a>
# **findByCode**
> VenteDto findByCode(code)

Rechercher une vente par son code

Cette méthode permet de chercher une vente par son code

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.VentesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8081");

    VentesApi apiInstance = new VentesApi(defaultClient);
    String code = "code_example"; // String | 
    try {
      VenteDto result = apiInstance.findByCode(code);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling VentesApi#findByCode");
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

[**VenteDto**](VenteDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Vente trouvée avec succès |  -  |
| **404** | Aucune vente trouvée avec ce code |  -  |

<a id="findById"></a>
# **findById**
> VenteDto findById(id)

Rechercher une vente par son ID

Cette méthode permet de chercher une vente par son identifiant

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.VentesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8081");

    VentesApi apiInstance = new VentesApi(defaultClient);
    Integer id = 56; // Integer | 
    try {
      VenteDto result = apiInstance.findById(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling VentesApi#findById");
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

[**VenteDto**](VenteDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Vente trouvée avec succès |  -  |
| **404** | Aucune vente trouvée avec cet identifiant |  -  |

<a id="save"></a>
# **save**
> VenteDto save(venteDto)

Enregistrer ou modifier une vente

Cette méthode permet de créer ou modifier une vente

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.VentesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8081");

    VentesApi apiInstance = new VentesApi(defaultClient);
    VenteDto venteDto = new VenteDto(); // VenteDto | 
    try {
      VenteDto result = apiInstance.save(venteDto);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling VentesApi#save");
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
| **venteDto** | [**VenteDto**](VenteDto.md)|  | |

### Return type

[**VenteDto**](VenteDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Vente non valide |  -  |
| **200** | Vente créée ou modifiée avec succès |  -  |

