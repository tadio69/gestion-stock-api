# ImglinkApi

All URIs are relative to *http://localhost:8081*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**delete2**](ImglinkApi.md#delete2) | **DELETE** /gestionstock/v1/photos/delete/{id} | Supprimer un Imglink par son ID |
| [**findAll2**](ImglinkApi.md#findAll2) | **GET** /gestionstock/v1/photos/all | Lister tous les Imglinks |
| [**findById2**](ImglinkApi.md#findById2) | **GET** /gestionstock/v1/photos/{id} | Rechercher un Imglink par son ID |
| [**uploadImage**](ImglinkApi.md#uploadImage) | **POST** /gestionstock/v1/photos/upload | Téléverser une photo |


<a id="delete2"></a>
# **delete2**
> delete2(id)

Supprimer un Imglink par son ID

Cette méthode permet de supprimer un imglink par son identifiant

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ImglinkApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8081");

    ImglinkApi apiInstance = new ImglinkApi(defaultClient);
    Integer id = 56; // Integer | 
    try {
      apiInstance.delete2(id);
    } catch (ApiException e) {
      System.err.println("Exception when calling ImglinkApi#delete2");
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
| **200** | Imglink supprimé avec succès |  -  |

<a id="findAll2"></a>
# **findAll2**
> List&lt;ImgLinkDto&gt; findAll2()

Lister tous les Imglinks

Cette méthode permet de retourner la liste des imglinks

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ImglinkApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8081");

    ImglinkApi apiInstance = new ImglinkApi(defaultClient);
    try {
      List<ImgLinkDto> result = apiInstance.findAll2();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ImglinkApi#findAll2");
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

[**List&lt;ImgLinkDto&gt;**](ImgLinkDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Liste des imglinks retournée avec succès |  -  |

<a id="findById2"></a>
# **findById2**
> ImgLinkDto findById2(id)

Rechercher un Imglink par son ID

Cette méthode permet de chercher un Imglink par son identifiant

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ImglinkApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8081");

    ImglinkApi apiInstance = new ImglinkApi(defaultClient);
    Integer id = 56; // Integer | 
    try {
      ImgLinkDto result = apiInstance.findById2(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ImglinkApi#findById2");
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

[**ImgLinkDto**](ImgLinkDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **404** | Aucun Imglink trouvé avec cet identifiant |  -  |
| **200** | Imglink trouvé avec succès |  -  |

<a id="uploadImage"></a>
# **uploadImage**
> ImgLinkDto uploadImage(identreprise, _file)

Téléverser une photo

Cette méthode permet de téléverser une photo dans ImgLink

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ImglinkApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8081");

    ImglinkApi apiInstance = new ImglinkApi(defaultClient);
    Integer identreprise = 56; // Integer | 
    File _file = new File("/path/to/file"); // File | 
    try {
      ImgLinkDto result = apiInstance.uploadImage(identreprise, _file);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ImglinkApi#uploadImage");
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
| **identreprise** | **Integer**|  | |
| **_file** | **File**|  | |

### Return type

[**ImgLinkDto**](ImgLinkDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Photo téléversée avec succès |  -  |
| **400** | Données de photo non valides |  -  |

