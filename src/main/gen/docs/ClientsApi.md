# ClientsApi

All URIs are relative to *http://localhost:8081*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**delete8**](ClientsApi.md#delete8) | **DELETE** /gestionstock/v1/clients/delete/{id} | Supprimer un client par son ID |
| [**findAll8**](ClientsApi.md#findAll8) | **GET** /gestionstock/v1/clients/all | Renvoyer la liste des clients |
| [**findById8**](ClientsApi.md#findById8) | **GET** /gestionstock/v1/clients/{id} | Rechercher un client par son ID |
| [**findByNom2**](ClientsApi.md#findByNom2) | **GET** /gestionstock/v1/clients/nom/{nom} | Rechercher un client par son nom |
| [**save7**](ClientsApi.md#save7) | **POST** /gestionstock/v1/clients/create | Enregistrer ou modifier un client |


<a id="delete8"></a>
# **delete8**
> delete8(id)

Supprimer un client par son ID

Cette méthode permet de supprimer un client par son identifiant

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ClientsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8081");

    ClientsApi apiInstance = new ClientsApi(defaultClient);
    Integer id = 56; // Integer | 
    try {
      apiInstance.delete8(id);
    } catch (ApiException e) {
      System.err.println("Exception when calling ClientsApi#delete8");
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
| **200** | Client supprimé avec succès |  -  |

<a id="findAll8"></a>
# **findAll8**
> List&lt;ClientDto&gt; findAll8()

Renvoyer la liste des clients

Cette méthode permet de renvoyer la liste des clients enregistrés

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ClientsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8081");

    ClientsApi apiInstance = new ClientsApi(defaultClient);
    try {
      List<ClientDto> result = apiInstance.findAll8();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ClientsApi#findAll8");
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

[**List&lt;ClientDto&gt;**](ClientDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Liste des clients retournée avec succès |  -  |

<a id="findById8"></a>
# **findById8**
> ClientDto findById8(id)

Rechercher un client par son ID

Cette méthode permet de chercher un client par son identifiant

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ClientsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8081");

    ClientsApi apiInstance = new ClientsApi(defaultClient);
    Integer id = 56; // Integer | 
    try {
      ClientDto result = apiInstance.findById8(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ClientsApi#findById8");
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

[**ClientDto**](ClientDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **404** | Aucun client trouvé avec cet ID |  -  |
| **200** | Client trouvé avec succès |  -  |

<a id="findByNom2"></a>
# **findByNom2**
> ClientDto findByNom2(nom)

Rechercher un client par son nom

Cette méthode permet de chercher un client par son nom

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ClientsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8081");

    ClientsApi apiInstance = new ClientsApi(defaultClient);
    String nom = "nom_example"; // String | 
    try {
      ClientDto result = apiInstance.findByNom2(nom);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ClientsApi#findByNom2");
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

[**ClientDto**](ClientDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Client trouvé avec succès |  -  |
| **404** | Aucun client trouvé avec ce nom |  -  |

<a id="save7"></a>
# **save7**
> ClientDto save7(clientDto)

Enregistrer ou modifier un client

Cette méthode permet d&#39;enregistrer ou de modifier un client

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ClientsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8081");

    ClientsApi apiInstance = new ClientsApi(defaultClient);
    ClientDto clientDto = new ClientDto(); // ClientDto | 
    try {
      ClientDto result = apiInstance.save7(clientDto);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ClientsApi#save7");
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
| **clientDto** | [**ClientDto**](ClientDto.md)|  | |

### Return type

[**ClientDto**](ClientDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Client créé ou modifié avec succès |  -  |
| **400** | Client non valide |  -  |

