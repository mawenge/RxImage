package com.image.rx.data.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.image.rx.data.Constant;
import com.image.rx.data.SingletonOkHttpClient;
import com.image.rx.data.entity.Gallery;
import com.image.rx.data.entity.Picture;
import com.image.rx.data.response.PhotoDetailResponse;
import com.image.rx.data.response.PhotoResponse;
import com.image.rx.data.rxjava.util.TransformerProvider;
import java.util.List;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Administrator on 2016/9/29.
 */

public class ServiceRest {
    private final PhotoService photoService;


    private static class SingletonHolder{
        private static ServiceRest instance = new ServiceRest();
    }

    public static ServiceRest getInstance(){
        return SingletonHolder.instance;
    }

    private ServiceRest(){
        Gson gson = new GsonBuilder().
                excludeFieldsWithoutExposeAnnotation().
                serializeNulls().create();


        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(SingletonOkHttpClient.getInstance())
                .build();

        photoService = retrofit.create(PhotoService.class);


    }


    /**
     * 获取全部图片
     * @param page
     * @param rows
     * @return
     */
    public Observable<List<Gallery>> getPhotoList(int page, int rows){
        return photoService.getPhotoList(page, rows).map(new Func1<PhotoResponse, List<Gallery>>() {
            @Override
            public List<Gallery> call(PhotoResponse photoResponse) {
                return photoResponse.getPhotoList();
            }
        });
    }

    /**
     * 获取特定分类下的图片
     * @param page
     * @param rows
     * @param id
     * @return
     */
    public Observable<List<Gallery>> getPhotoList(int page, int rows, long id){
        return photoService.getPhotoList(page, rows, id)
                .compose(TransformerProvider.<PhotoResponse>getErrorDetectorTransformer())
                .map(new Func1<PhotoResponse, List<Gallery>>() {
                    @Override
                    public List<Gallery> call(PhotoResponse photoResponse) {
                        return photoResponse.getPhotoList();
                    }
                });
    }

    /**
     * 获取某一图片id下的所有图片
     * @param id
     * @return
     */
    public Observable<List<Picture>> getPictureList(long id){
        return photoService.getPictureList(id)
                .compose(TransformerProvider.<PhotoDetailResponse>getErrorDetectorTransformer())
                .map(new Func1<PhotoDetailResponse, List<Picture>>() {
            @Override
            public List<Picture> call(PhotoDetailResponse photoDetailResponse) {
                return photoDetailResponse.getPictures();
            }
        });
    }


}
