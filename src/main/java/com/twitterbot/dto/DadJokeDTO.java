package com.twitterbot.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.twitterbot.deserializer.BooleanDeserializer;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DadJokeDTO {
	@JsonIgnore
	private boolean success;
	private Joke[] body;

	public Joke[] getBody() {
		return body;
	}

	public void setBody(Joke[] body) {
		this.body = body;
	}

	public static class Joke {
		public String get_id() {
			return _id;
		}

		public void set_id(String _id) {
			this._id = _id;
		}

		public String getSetup() {
			return setup;
		}

		public void setSetup(String setup) {
			this.setup = setup;
		}

		public String getPunchline() {
			return punchline;
		}

		public void setPunchline(String punchline) {
			this.punchline = punchline;
		}

		public Author getAuthor() {
			return author;
		}

		public void setAuthor(Author author) {
			this.author = author;
		}

		public boolean isApproved() {
			return approved;
		}

		public void setApproved(boolean approved) {
			this.approved = approved;
		}

		public long getDate() {
			return date;
		}

		public void setDate(long date) {
			this.date = date;
		}


		public String getShareableLink() {
			return shareableLink;
		}

		public void setShareableLink(String shareableLink) {
			this.shareableLink = shareableLink;
		}

		private String _id;
		private String setup;
		private String punchline;
		private Author author;
		private boolean approved;
		private long date;
		@JsonDeserialize(using = BooleanDeserializer.class)
		private boolean NSFW;
		private String shareableLink;
		@JsonIgnore
		private String type;
		@JsonIgnore
		private int likes;

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public int getLikes() {
			return likes;
		}

		public void setLikes(int likes) {
			this.likes = likes;
		}

		public static class Author {
			private String name;
			private String id;

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public String getId() {
				return id;
			}

			public void setId(String id) {
				this.id = id;
			}
		}
	}

}
