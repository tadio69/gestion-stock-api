# FournisseurApi

All URIs are relative to *http://localhost:8081*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**delete4**](FournisseurApi.md#delete4) | **DELETE** /gestionstock/v1/fournisseurs/delete/{id} | Supprimer un fournisseur par son ID |
| [**findAll4**](FournisseurApi.md#findAll4) | **GET** /gestionstock/v1/fournisseurs/all | Renvoyer la liste des fournisseurs |
| [**findById4**](FournisseurApi.md#findById4) | **GET** /gestionstock/v1/fournisseurs/{id} | Rechercher un fourniseur par ID |
| [**findByNom1**](FournisseurApi.md#findByNom1) | **GET** /gestionstock/v1/fournisseurs/nom/{nom} | Rechercher un fournisseur par son nom |
| [**save3**](FournisseurApi.md#save3) | **POST** /gestionstock/v1/fournisseurs/create | Enregistrer ou modifier un fournisseur |


<a id="delete4"></a>
# **delete4**
> delete4(id)

Supprimer un fournisseur par son ID

Cette méthode permet de supprimer un fournisseur par son identifiant

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.FournisseurApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8081");

    FournisseurApi apiInstance = new FournisseurApi(defaultClient);
    Integer id = 56; // Integer | 
    try {
      apiInstance.delete4(id);
    } catch (ApiException e) {
      System.err.println("Exception when calling FournisseurApi#delete4");
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
| **200** | Fournisseur supprimé avec succès |  -  |

<a id="findAll4"></a>
# **findAll4**
> List&lt;FournisseurDto&gt; findAll4()

Renvoyer la liste des fournisseurs

Cette méthode permet de retourner la liste des fournisseurs enregistrés

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.FournisseurApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8081");

    FournisseurApi apiInstance = new FournisseurApi(defaultClient);
    try {
      List<FournisseurDto> result = apiInstance.findAll4();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling FournisseurApi#findAll4");
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

[**List&lt;FournisseurDto&gt;**](FournisseurDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Liste des fournisseurs retournée avec succès |  -  |

<a id="findById4"></a>
# **findById4**
> FournisseurDto findById4(id)

Rechercher un fourniseur par ID

Cette méthode permet de chercher un fournisseur par son identifiant

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.FournisseurApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8081");

    FournisseurApi apiInstance = new FournisseurApi(defaultClient);
    Integer id = 56; // Integer | 
    try {
      FournisseurDto result = apiInstance.findById4(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling FournisseurApi#findById4");
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

[**FournisseurDto**](FournisseurDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Fournisseur trouvé avec succès |  -  |
| **404** | Aucun fournisseur trouvé avec cet identifiant |  -  |

<a id="findByNom1"></a>
# **findByNom1**
> FournisseurDto findByNom1(nom)

Rechercher un fournisseur par son nom

Cette méthode permet de chercher un fournisseur par son nom

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.FournisseurApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8081");

    FournisseurApi apiInstance = new FournisseurApi(defaultClient);
    String nom = "nom_example"; // String | 
    try {
      FournisseurDto result = apiInstance.findByNom1(nom);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling FournisseurApi#findByNom1");
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
| **nom** | **String**|  | |

### Return type

[**FournisseurDto**](FournisseurDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Fournisseur trouvé avec succès |  -  |
| **404** | Aucun fournisseur trouvé avec ce nom |  -  |

<a id="save3"></a>
# **save3**
> FournisseurDto save3(fournisseurDto)

Enregistrer ou modifier un fournisseur

Cette méthode permet de créer ou de modifier un fournisseur

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.FournisseurApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8081");

    FournisseurApi apiInstance = new FournisseurApi(defaultClient);
    FournisseurDto fournisseurDto = new FournisseurDto(); // FournisseurDto | 
    try {
      FournisseurDto result = apiInstance.save3(fournisseurDto);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling FournisseurApi#save3");
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
| **fournisseurDto** | [**FournisseurDto**](FournisseurDto.md)|  | |

### Return type

[**FournisseurDto**](FournisseurDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Entreprise créée ou modifiée avec succès |  -  |
| **400** | Fournisseur non valide |  -  |

