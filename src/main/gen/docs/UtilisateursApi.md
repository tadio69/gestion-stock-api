# UtilisateursApi

All URIs are relative to *http://localhost:8081*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**delete1**](UtilisateursApi.md#delete1) | **DELETE** /gestionstock/v1/utilisateurs/delete/{id} | Supprimer un utilisateur par son ID |
| [**findAll1**](UtilisateursApi.md#findAll1) | **GET** /gestionstock/v1/utilisateurs/all | Renvoyer la liste des utilisateurs |
| [**findById1**](UtilisateursApi.md#findById1) | **GET** /gestionstock/v1/utilisateurs/{id} | Rechercher un utilisateur par son ID |
| [**findByNom**](UtilisateursApi.md#findByNom) | **GET** /gestionstock/v1/utilisateurs/nom/{nom} | Rechercher un utilisateur par son nom |
| [**save1**](UtilisateursApi.md#save1) | **POST** /gestionstock/v1/utilisateurs/create | Enregistrer ou modifier un utilisateur |


<a id="delete1"></a>
# **delete1**
> delete1(id)

Supprimer un utilisateur par son ID

Cette permet de supprimer un utilisateur par son identifiant

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UtilisateursApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8081");

    UtilisateursApi apiInstance = new UtilisateursApi(defaultClient);
    Integer id = 56; // Integer | 
    try {
      apiInstance.delete1(id);
    } catch (ApiException e) {
      System.err.println("Exception when calling UtilisateursApi#delete1");
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
| **200** | Utilisateur supprimé avec succès |  -  |

<a id="findAll1"></a>
# **findAll1**
> List&lt;UtilisateurDto&gt; findAll1()

Renvoyer la liste des utilisateurs

Cette méthode permet de retourner la liste des utilisateurs enregistrés

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UtilisateursApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8081");

    UtilisateursApi apiInstance = new UtilisateursApi(defaultClient);
    try {
      List<UtilisateurDto> result = apiInstance.findAll1();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UtilisateursApi#findAll1");
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

[**List&lt;UtilisateurDto&gt;**](UtilisateurDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Liste des utilisateurs retournée avec succès |  -  |

<a id="findById1"></a>
# **findById1**
> UtilisateurDto findById1(id)

Rechercher un utilisateur par son ID

Cette méthode permet de chercher un utilisateur par son identifiant

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UtilisateursApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8081");

    UtilisateursApi apiInstance = new UtilisateursApi(defaultClient);
    Integer id = 56; // Integer | 
    try {
      UtilisateurDto result = apiInstance.findById1(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UtilisateursApi#findById1");
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

[**UtilisateurDto**](UtilisateurDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Utilisateur trouvé avec succès |  -  |
| **404** | Aucun utilisateur trouvé avec cet identifiant |  -  |

<a id="findByNom"></a>
# **findByNom**
> UtilisateurDto findByNom(nom)

Rechercher un utilisateur par son nom

Cette méthode permet de chercher un utilisateur par son nom

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UtilisateursApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8081");

    UtilisateursApi apiInstance = new UtilisateursApi(defaultClient);
    String nom = "nom_example"; // String | 
    try {
      UtilisateurDto result = apiInstance.findByNom(nom);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UtilisateursApi#findByNom");
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

[**UtilisateurDto**](UtilisateurDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **404** | Aucun utilisateur trouvé avec ce nom |  -  |
| **200** | Utilisateur trouvé avec succès |  -  |

<a id="save1"></a>
# **save1**
> UtilisateurDto save1(utilisateurDto)

Enregistrer ou modifier un utilisateur

Cette méthode permet de créer ou modifier un utilisateur

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UtilisateursApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8081");

    UtilisateursApi apiInstance = new UtilisateursApi(defaultClient);
    UtilisateurDto utilisateurDto = new UtilisateurDto(); // UtilisateurDto | 
    try {
      UtilisateurDto result = apiInstance.save1(utilisateurDto);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UtilisateursApi#save1");
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
| **utilisateurDto** | [**UtilisateurDto**](UtilisateurDto.md)|  | |

### Return type

[**UtilisateurDto**](UtilisateurDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Utilisateur créé ou modifié avec succès |  -  |
| **400** | Utilisateur non valide |  -  |

