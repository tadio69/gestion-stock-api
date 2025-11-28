# MvtStocksApi

All URIs are relative to *http://localhost:8081*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**delete3**](MvtStocksApi.md#delete3) | **DELETE** /gestionstock/v1/mvtstocks/delete/{id} | Supprimer un mvtstock par son ID |
| [**findAll3**](MvtStocksApi.md#findAll3) | **GET** /gestionstock/v1/mvtstocks/all | Renvoyer la liste des mvtstocks |
| [**findById3**](MvtStocksApi.md#findById3) | **GET** /gestionstock/v1/mvtstocks/{id} | Rechercher un mvtstock par son ID |
| [**save2**](MvtStocksApi.md#save2) | **POST** /gestionstock/v1/mvtstocks/create | Enregistrer ou modifier un mvtstock |


<a id="delete3"></a>
# **delete3**
> delete3(id)

Supprimer un mvtstock par son ID

Cette méthode permet de supprimer un mouvement de stock

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.MvtStocksApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8081");

    MvtStocksApi apiInstance = new MvtStocksApi(defaultClient);
    Integer id = 56; // Integer | 
    try {
      apiInstance.delete3(id);
    } catch (ApiException e) {
      System.err.println("Exception when calling MvtStocksApi#delete3");
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
| **200** | Mouvement de stock supprimé avec succès |  -  |

<a id="findAll3"></a>
# **findAll3**
> List&lt;MvtStockDto&gt; findAll3()

Renvoyer la liste des mvtstocks

Cette méthode permet de retourner la liste des mouvements de stock

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.MvtStocksApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8081");

    MvtStocksApi apiInstance = new MvtStocksApi(defaultClient);
    try {
      List<MvtStockDto> result = apiInstance.findAll3();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MvtStocksApi#findAll3");
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

[**List&lt;MvtStockDto&gt;**](MvtStockDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Liste des mouvements de stock retournée avec succès |  -  |

<a id="findById3"></a>
# **findById3**
> MvtStockDto findById3(id)

Rechercher un mvtstock par son ID

Cette méthode permet de chercher un mouvement de stock par identifiant

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.MvtStocksApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8081");

    MvtStocksApi apiInstance = new MvtStocksApi(defaultClient);
    Integer id = 56; // Integer | 
    try {
      MvtStockDto result = apiInstance.findById3(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MvtStocksApi#findById3");
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

[**MvtStockDto**](MvtStockDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **404** | Aucun mouvement de stock trouvé avec cet identifiant |  -  |
| **200** | Mouvement de stock trouvé avec succès |  -  |

<a id="save2"></a>
# **save2**
> MvtStockDto save2(mvtStockDto)

Enregistrer ou modifier un mvtstock

Cette méthode permet de créer ou de modifier un mouvement de stock

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.MvtStocksApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8081");

    MvtStocksApi apiInstance = new MvtStocksApi(defaultClient);
    MvtStockDto mvtStockDto = new MvtStockDto(); // MvtStockDto | 
    try {
      MvtStockDto result = apiInstance.save2(mvtStockDto);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MvtStocksApi#save2");
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
| **mvtStockDto** | [**MvtStockDto**](MvtStockDto.md)|  | |

### Return type

[**MvtStockDto**](MvtStockDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Mouvement de stock non valide |  -  |
| **200** | Mouvement de stock créé ou modifié avec succès |  -  |

