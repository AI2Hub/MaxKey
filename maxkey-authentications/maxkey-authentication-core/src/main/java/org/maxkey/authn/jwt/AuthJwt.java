/*
 * Copyright [2022] [MaxKey of copyright http://www.maxkey.top]
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 

package org.maxkey.authn.jwt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.maxkey.authn.SigninPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class AuthJwt implements Serializable {
	
	private static final long serialVersionUID = -914373258878811144L;
	
	private String ticket;
	private String token;
	private String type = "Bearer";
	private String id;
	private String name;
	private String username;
	private String displayName;
	private String email;
	private String instId;
	private String instName;
	private List<String> authorities;
	  
	  
	public AuthJwt(String token, String id, String username, String displayName, String email, String instId,
			String instName, List<String> authorities) {
		this.token = token;
		this.id = id;
		this.name = username;
		this.username = username;
		this.displayName = displayName;
		this.email = email;
		this.instId = instId;
		this.instName = instName;
		this.authorities = authorities;
	}
	
	public AuthJwt(String token, Authentication  authentication) {
		SigninPrincipal principal = ((SigninPrincipal)authentication.getPrincipal());
		
		this.token = token;
		this.ticket = principal.getOnlineTicket().getTicketId();
		
		this.id = principal.getUserInfo().getId();
		this.username = principal.getUserInfo().getUsername();
		this.name = this.username;
		this.displayName = principal.getUserInfo().getDisplayName();
		this.email = principal.getUserInfo().getEmail();
		this.instId = principal.getUserInfo().getInstId();
		this.instName = principal.getUserInfo().getInstName();
		
		this.authorities = new ArrayList<String>();
		for(GrantedAuthority grantedAuthority :authentication.getAuthorities()) {
			this.authorities.add(grantedAuthority.getAuthority());
		}
	}
	
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getInstId() {
		return instId;
	}
	public void setInstId(String instId) {
		this.instId = instId;
	}
	public String getInstName() {
		return instName;
	}
	public void setInstName(String instName) {
		this.instName = instName;
	}
	public List<String> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(List<String> authorities) {
		this.authorities = authorities;
	}
	
	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AuthJwt [token=");
		builder.append(token);
		builder.append(", type=");
		builder.append(type);
		builder.append(", id=");
		builder.append(id);
		builder.append(", username=");
		builder.append(username);
		builder.append(", displayName=");
		builder.append(displayName);
		builder.append(", email=");
		builder.append(email);
		builder.append(", instId=");
		builder.append(instId);
		builder.append(", instName=");
		builder.append(instName);
		builder.append(", authorities=");
		builder.append(authorities);
		builder.append("]");
		return builder.toString();
	}
	  
	  
	  
}
