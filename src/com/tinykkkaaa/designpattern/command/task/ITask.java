package com.tinykkkaaa.designpattern.command.task;

import com.tinykkkaaa.designpattern.command.Programmer;

public interface ITask {
	void handle();
	void setProgrammer(Programmer programmer);
}
