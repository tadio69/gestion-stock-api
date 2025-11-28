# EntreprisesApi

All URIs are relative to *http://localhost:8081*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**delete5**](EntreprisesApi.md#delete5) | **DELETE** /gestionstock/v1/entreprises/delete/{id} | Supprimer une entreprise par son ID |
| [**findAll5**](EntreprisesApi.md#findAll5) | **GET** /gestionstock/v1/entreprises/all | Renvoyer la liste des entreprises |
| [**findByCodefiscal**](EntreprisesApi.md#findByCodefiscal) | **GET** /gestionstock/v1/entreprises/codefiscal/{codefiscal} | Rechercher une entreprise par son code fiscal |
| [**findByDescription**](EntreprisesApi.md#findByDescription) | **GET** /gestionstock/v1/entreprises/description/{description} | Rechercher une entreprise par sa description |
| [**findById5**](EntreprisesApi.md#findById5) | **GET** /gestionstock/v1/entreprises/{id} | Rechercher une entreprise par son ID |
| [**save4**](EntreprisesApi.md#save4) | **POST** /gestionstock/v1/entreprises/create | Enregistrer ou modifier une entreprise |


<a id="delete5"></a>
# **delete5**
> delete5(id)

Supprimer une entreprise par son ID

Cette méthode permet de supprimer une entreprise par son identifiant

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.EntreprisesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8081");

    EntreprisesApi apiInstance = new EntreprisesApi(defaultClient);
    Integer id = 56; // Integer | 
    try {
      apiInstance.delete5(id);
    } catch (ApiException e) {
      System.err.println("Exception when calling EntreprisesApi#delete5");
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
| **200** | Entreprise supprimée avec succès |  -  |

<a id="findAll5"></a>
# **findAll5**
> List&lt;EntrepriseDto&gt; findAll5()

Renvoyer la liste des entreprises

Cette méthode permet de retourner la liste des entreprises enregistrées

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.EntreprisesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8081");

    EntreprisesApi apiInstance = new EntreprisesApi(defaultClient);
    try {
      List<EntrepriseDto> result = apiInstance.findAll5();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling EntreprisesApi#findAll5");
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

[**List&lt;EntrepriseDto&gt;**](EntrepriseDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Liste des entreprises retournée avec succès |  -  |

<a id="findByCodefiscal"></a>
# **findByCodefiscal**
> EntrepriseDto findByCodefiscal(codefiscal)

Rechercher une entreprise par son code fiscal

Cette méthode permet de chercher une entreprise par son code fiscal

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.EntreprisesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8081");

    EntreprisesApi apiInstance = new EntreprisesApi(defaultClient);
    String codefiscal = "codefiscal_example"; // String | 
    try {
      EntrepriseDto result = apiInstance.findByCodefiscal(codefiscal);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling EntreprisesApi#findByCodefiscal");
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
| **codefiscal** | **String**|  | |

### Return type

[**EntrepriseDto**](EntrepriseDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **404** | Aucune entreprise trouvée avec ce code fiscal |  -  |
| **200** | Entreprise trouvée avec succès |  -  |

<a id="findByDescription"></a>
# **findByDescription**
> EntrepriseDto findByDescription(description)

Rechercher une entreprise par sa description

Cette méthode permet de chercher uen entreprise par sa description

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.EntreprisesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8081");

    EntreprisesApi apiInstance = new EntreprisesApi(defaultClient);
    String description = "description_example"; // String | 
    try {
      EntrepriseDto result = apiInstance.findByDescription(description);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling EntreprisesApi#findByDescription");
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
| **description** | **String**|  | |

### Return type

[**EntrepriseDto**](EntrepriseDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Entreprise trouvée avec succès |  -  |
| **404** | Aucune entreprise trouvée avec cette description |  -  |

<a id="findById5"></a>
# **findById5**
> EntrepriseDto findById5(id)

Rechercher une entreprise par son ID

Cette méthode permet de chercher une entreprise par son identifiant

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.EntreprisesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8081");

    EntreprisesApi apiInstance = new EntreprisesApi(defaultClient);
    Integer id = 56; // Integer | 
    try {
      EntrepriseDto result = apiInstance.findById5(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling EntreprisesApi#findById5");
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

[**EntrepriseDto**](EntrepriseDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Entreprise trouvée avec succès |  -  |
| **404** | Aucune entreprise trouvée avec cet identifiant |  -  |

<a id="save4"></a>
# **save4**
> EntrepriseDto save4(entrepriseDto)

Enregistrer ou modifier une entreprise

Cette méthode permet de créer ou de modifier une entreprise

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.EntreprisesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8081");

    EntreprisesApi apiInstance = new EntreprisesApi(defaultClient);
    EntrepriseDto entrepriseDto = new EntrepriseDto(); // EntrepriseDto | 
    try {
      EntrepriseDto result = apiInstance.save4(entrepriseDto);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling EntreprisesApi#save4");
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
| **entrepriseDto** | [**EntrepriseDto**](EntrepriseDto.md)|  | |

### Return type

[**EntrepriseDto**](EntrepriseDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Entreprise créée ou modifiée avec succès |  -  |
| **400** | Entreprise non valide |  -  |

