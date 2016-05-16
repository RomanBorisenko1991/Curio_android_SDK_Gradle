/*
 * Copyright (C) 2014 Turkcell
 * 
 * Created by Can Ciloglu on 10 Haz 2014
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.turkcell.curio.model;

/**
 * Model class for screen request structure.
 * 
 * @author Can Ciloglu
 *
 */
public class Screen {
	private String hitCode;
	private String title;
	private String path;
	
	public Screen(String hitCode, String title, String path) {
		this.hitCode = hitCode;
		this.title = title;
		this.path = path;
	}

	public String getHitCode() {
		return hitCode;
	}

	public void setHitCode(String hitCode) {
		this.hitCode = hitCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
