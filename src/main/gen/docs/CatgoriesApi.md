# CatgoriesApi

All URIs are relative to *http://localhost:8081*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**delete9**](CatgoriesApi.md#delete9) | **DELETE** /gestionstock/v1/categories/delete/{id} | Supprimer une catégorie par son ID |
| [**findAll9**](CatgoriesApi.md#findAll9) | **GET** /gestionstock/v1/categories/all | Renvoyer la liste des catégories |
| [**findByCode3**](CatgoriesApi.md#findByCode3) | **GET** /gestionstock/v1/categories/code/{code} | Rechercher une catégorie par son code |
| [**findByDesignation**](CatgoriesApi.md#findByDesignation) | **GET** /gestionstock/v1/categories/designation/{designation} | Rechercher une catégorie par sa désignation |
| [**findById9**](CatgoriesApi.md#findById9) | **GET** /gestionstock/v1/categories/{id} | Rechercher une catégorie par son ID |
| [**save8**](CatgoriesApi.md#save8) | **POST** /gestionstock/v1/categories/create | Enregistrer ou modifier une catégorie |


<a id="delete9"></a>
# **delete9**
> delete9(id)

Supprimer une catégorie par son ID

Cette méthode permet de supprimer une catégorie par son identifiant

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CatgoriesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8081");

    CatgoriesApi apiInstance = new CatgoriesApi(defaultClient);
    Integer id = 56; // Integer | 
    try {
      apiInstance.delete9(id);
    } catch (ApiException e) {
      System.err.println("Exception when calling CatgoriesApi#delete9");
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
| **200** | Catégorie supprimée avec succès |  -  |

<a id="findAll9"></a>
# **findAll9**
> List&lt;CategorieDto&gt; findAll9()

Renvoyer la liste des catégories

Cette méthode retourne la liste des catégories enregistrées

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CatgoriesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8081");

    CatgoriesApi apiInstance = new CatgoriesApi(defaultClient);
    try {
      List<CategorieDto> result = apiInstance.findAll9();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CatgoriesApi#findAll9");
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

[**List&lt;CategorieDto&gt;**](CategorieDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Liste des catégories retournées avec succès |  -  |

<a id="findByCode3"></a>
# **findByCode3**
> CategorieDto findByCode3(code)

Rechercher une catégorie par son code

Cette méthode permet de chercher une catégorie par son code

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CatgoriesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8081");

    CatgoriesApi apiInstance = new CatgoriesApi(defaultClient);
    String code = "code_example"; // String | 
    try {
      CategorieDto result = apiInstance.findByCode3(code);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CatgoriesApi#findByCode3");
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

[**CategorieDto**](CategorieDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **404** | Aucune catégorie trouvée avec ce code |  -  |
| **200** | Catégorie trouvée avec succès |  -  |

<a id="findByDesignation"></a>
# **findByDesignation**
> CategorieDto findByDesignation(designation)

Rechercher une catégorie par sa désignation

Cette méthode permet de chercher une catégorie par sa désignation

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CatgoriesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8081");

    CatgoriesApi apiInstance = new CatgoriesApi(defaultClient);
    String designation = "designation_example"; // String | 
    try {
      CategorieDto result = apiInstance.findByDesignation(designation);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CatgoriesApi#findByDesignation");
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
| **designation** | **String**|  | |

### Return type

[**CategorieDto**](CategorieDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Catégorie trouvée avec succès |  -  |
| **404** | Aucune catégorie trouvée avec cette désignation |  -  |

<a id="findById9"></a>
# **findById9**
> CategorieDto findById9(id)

Rechercher une catégorie par son ID

Cette méthode permet de chercher une catégorie via son identifiant

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CatgoriesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8081");

    CatgoriesApi apiInstance = new CatgoriesApi(defaultClient);
    Integer id = 56; // Integer | 
    try {
      CategorieDto result = apiInstance.findById9(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CatgoriesApi#findById9");
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

[**CategorieDto**](CategorieDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **404** | Aucune catégorie trouvée pour cet ID |  -  |
| **200** | Catégorie trouvée avec succès |  -  |

<a id="save8"></a>
# **save8**
> CategorieDto save8(categorieDto)

Enregistrer ou modifier une catégorie

Cette méthode permet d&#39;enregistrer ou de modifier une catégorie

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CatgoriesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8081");

    CatgoriesApi apiInstance = new CatgoriesApi(defaultClient);
    CategorieDto categorieDto = new CategorieDto(); // CategorieDto | 
    try {
      CategorieDto result = apiInstance.save8(categorieDto);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CatgoriesApi#save8");
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
| **categorieDto** | [**CategorieDto**](CategorieDto.md)|  | |

### Return type

[**CategorieDto**](CategorieDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Catégorie créée ou modifiée avec succès |  -  |
| **400** | Catégorie non valide |  -  |

