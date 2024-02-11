/*
 * MantisBT REST API
 * For the sandbox to work, MantisBT must be hosted at the root folder of the host. For example: http://mantishost/ rather http://host/mantisbt.  If that is not the case, then create a host alias to map it as such or edit swagger.json to change basePath to include the mantisbt folder name.
 *
 * OpenAPI spec version: 1.1.1
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package io.swagger.client.api;

import io.swagger.client.ApiCallback;
import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.ApiResponse;
import io.swagger.client.Configuration;
import io.swagger.client.Pair;
import io.swagger.client.ProgressRequestBody;
import io.swagger.client.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;


import io.swagger.client.model.LangGetResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocalizationApi {
    private ApiClient apiClient;

    public LocalizationApi() {
        this(Configuration.getDefaultApiClient());
    }

    public LocalizationApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Build call for langGet
     * @param string An array of localized labels given their name string lang/strings_english.txt folder in MantisBT.  The name doesn&#39;t include $s_ prefix. (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call langGetCall(List<String> string, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/lang";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (string != null)
        localVarCollectionQueryParams.addAll(apiClient.parameterToPairs("csv", "string", string));

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] { "Authorization" };
        return apiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }

    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call langGetValidateBeforeCall(List<String> string, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'string' is set
        if (string == null) {
            throw new ApiException("Missing the required parameter 'string' when calling langGet(Async)");
        }
        

        com.squareup.okhttp.Call call = langGetCall(string, progressListener, progressRequestListener);
        return call;

    }

    /**
     * Get localized strings
     * Gets a set of localized strings in context of the logged in user&#39;s language.  If a localized string is not defined, then it will be filtered out.
     * @param string An array of localized labels given their name string lang/strings_english.txt folder in MantisBT.  The name doesn&#39;t include $s_ prefix. (required)
     * @return LangGetResponse
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public LangGetResponse langGet(List<String> string) throws ApiException {
        ApiResponse<LangGetResponse> resp = langGetWithHttpInfo(string);
        return resp.getData();
    }

    /**
     * Get localized strings
     * Gets a set of localized strings in context of the logged in user&#39;s language.  If a localized string is not defined, then it will be filtered out.
     * @param string An array of localized labels given their name string lang/strings_english.txt folder in MantisBT.  The name doesn&#39;t include $s_ prefix. (required)
     * @return ApiResponse&lt;LangGetResponse&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<LangGetResponse> langGetWithHttpInfo(List<String> string) throws ApiException {
        com.squareup.okhttp.Call call = langGetValidateBeforeCall(string, null, null);
        Type localVarReturnType = new TypeToken<LangGetResponse>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Get localized strings (asynchronously)
     * Gets a set of localized strings in context of the logged in user&#39;s language.  If a localized string is not defined, then it will be filtered out.
     * @param string An array of localized labels given their name string lang/strings_english.txt folder in MantisBT.  The name doesn&#39;t include $s_ prefix. (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call langGetAsync(List<String> string, final ApiCallback<LangGetResponse> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = langGetValidateBeforeCall(string, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<LangGetResponse>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
}
