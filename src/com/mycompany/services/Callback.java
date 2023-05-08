/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

/**
 *
 * @author Nour Benkairia
 */
class Callback<T> {
     void onSuccess(T result){};
    void onError(Throwable t){};
}
