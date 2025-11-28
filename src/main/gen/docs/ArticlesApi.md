# ArticlesApi

All URIs are relative to *http://localhost:8081*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**delete10**](ArticlesApi.md#delete10) | **DELETE** /gestionstock/v1/articles/delete/{id} | Supprimer un article par son ID |
| [**findAll10**](ArticlesApi.md#findAll10) | **GET** /gestionstock/v1/articles/all | Renvoyer la liste des articles |
| [**findByCode4**](ArticlesApi.md#findByCode4) | **GET** /gestionstock/v1/articles/code/{code} | Rechercher un article par son code |
| [**findById10**](ArticlesApi.md#findById10) | **GET** /gestionstock/v1/articles/{id} | Rechercher un article par ID |
| [**save9**](ArticlesApi.md#save9) | **POST** /gestionstock/v1/articles/create | Enregistrer ou modifier un article |


<a id="delete10"></a>
# **delete10**
> delete10(id)

Supprimer un article par son ID

Cette méthode permet de supprimer un article par son identifiant

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ArticlesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8081");

    ArticlesApi apiInstance = new ArticlesApi(defaultClient);
    Integer id = 56; // Integer | 
    try {
      apiInstance.delete10(id);
    } catch (ApiException e) {
      System.err.println("Exception when calling ArticlesApi#delete10");
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
| **200** | Article supprimé avec succès |  -  |

<a id="findAll10"></a>
# **findAll10**
> List&lt;ArticleDto&gt; findAll10()

Renvoyer la liste des articles

Cette méthode retourne la liste des articles enregistrés

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ArticlesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8081");

    ArticlesApi apiInstance = new ArticlesApi(defaultClient);
    try {
      List<ArticleDto> result = apiInstance.findAll10();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ArticlesApi#findAll10");
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

[**List&lt;ArticleDto&gt;**](ArticleDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Liste des articles retournée avec succès |  -  |

<a id="findByCode4"></a>
# **findByCode4**
> ArticleDto findByCode4(code)

Rechercher un article par son code

Cette méthode permet de chercher un article par son code

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ArticlesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8081");

    ArticlesApi apiInstance = new ArticlesApi(defaultClient);
    String code = "code_example"; // String | 
    try {
      ArticleDto result = apiInstance.findByCode4(code);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ArticlesApi#findByCode4");
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

[**ArticleDto**](ArticleDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Article trouvé avec succès |  -  |
| **404** | Aucun article trouvé pour ce code |  -  |

<a id="findById10"></a>
# **findById10**
> ArticleDto findById10(id)

Rechercher un article par ID

Cette méthode permet de chercher un article via son identifiant

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ArticlesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8081");

    ArticlesApi apiInstance = new ArticlesApi(defaultClient);
    Integer id = 56; // Integer | 
    try {
      ArticleDto result = apiInstance.findById10(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ArticlesApi#findById10");
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

[**ArticleDto**](ArticleDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Article trouvé avec succès |  -  |
| **404** | Aucun article trouvé pour cet ID |  -  |

<a id="save9"></a>
# **save9**
> ArticleDto save9(articleDto)

Enregistrer ou modifier un article

Cette méthode permet d&#39;enregistrer ou modifier un article

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ArticlesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8081");

    ArticlesApi apiInstance = new ArticlesApi(defaultClient);
    ArticleDto articleDto = new ArticleDto(); // ArticleDto | 
    try {
      ArticleDto result = apiInstance.save9(articleDto);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ArticlesApi#save9");
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
| **articleDto** | [**ArticleDto**](ArticleDto.md)|  | |

### Return type

[**ArticleDto**](ArticleDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Article non valide |  -  |
| **200** | Article créé ou modifié avec succès |  -  |

