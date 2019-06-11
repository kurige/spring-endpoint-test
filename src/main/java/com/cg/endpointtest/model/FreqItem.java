package com.cg.endpointtest.model;

public class FreqItem {

    private String word;
    private Integer count;
    private Double frequency;

    public FreqItem(String word, Integer count, Double freq) {
        this.word = word;
        this.count = count;
        this.frequency = freq;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getFrequency() {
        return frequency;
    }

    public void setFrequency(Double frequency) {
        this.frequency = frequency;
    }
}
