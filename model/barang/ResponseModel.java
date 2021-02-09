package com.rizieq.barangku.model.barang;

import com.rizieq.barangku.model.barang.ResultItem;

import java.util.List;

public class ResponseModel {

    String kode, pesan;

    List<ResultItem> result;

    public List<ResultItem> getResult() {
        return result;
    }

    public void setResult(List<ResultItem> result) {
        this.result = result;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }
}
