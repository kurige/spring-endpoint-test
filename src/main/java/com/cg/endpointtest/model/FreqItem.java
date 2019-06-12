package com.cg.endpointtest.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FreqItem {

    @Id
    private String word;
    private Integer count;
    private Double frequency;

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
