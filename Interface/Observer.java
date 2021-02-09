package com.example.marketwatchsystem.Interface;

import com.example.marketwatchsystem.Model.Channel;

public interface Observer {
    void setCh(Channel ch);

    String getName();

    void setName(String name);

    void update();
}

