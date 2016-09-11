package io.github.TcFoxy.ArenaTOW.BattleArena.matches;

import io.github.TcFoxy.ArenaTOW.BattleArena.competition.match.MyMatch;
import mc.alk.arena.objects.MatchState;
import mc.alk.arena.objects.messaging.Channel;
import mc.alk.arena.objects.messaging.Channels;


public class MyMatchMessageEvent extends MyMatchEvent {
	final MatchState state;
	String serverMessage;
	String matchMessage;
	Channel serverChannel;

	public MyMatchMessageEvent(MyMatch match, MatchState state, Channel serverChannel, String serverMessage, String matchMessage) {
		super(match);
		this.serverChannel = serverChannel;
		this.serverMessage = serverMessage;
		this.matchMessage = matchMessage;
		this.state = state;
	}

	public String getServerMessage() {
		return serverMessage;
	}

	public void setServerMessage(String serverMessage) {
		this.serverMessage = serverMessage;
	}

	public String getMatchMessage() {
		return matchMessage;
	}

	public void setMatchMessage(String matchMessage) {
		this.matchMessage = matchMessage;
	}

	public Channel getServerChannel() {
		return serverChannel == null ? Channels.NullChannel : serverChannel;
	}

	public void setServerChannel(Channel serverChannel) {
		this.serverChannel = serverChannel;
	}
	public MatchState getState(){
		return state;
	}
}
