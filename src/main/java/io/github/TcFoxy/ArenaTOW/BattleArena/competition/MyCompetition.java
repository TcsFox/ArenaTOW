package io.github.TcFoxy.ArenaTOW.BattleArena.competition;

import mc.alk.arena.listeners.PlayerHolder;
import mc.alk.arena.listeners.custom.MethodController;
import mc.alk.arena.objects.CompetitionState;
import mc.alk.arena.objects.MatchParams;
import mc.alk.arena.objects.MatchState;
import mc.alk.arena.objects.arenas.ArenaListener;
import mc.alk.arena.objects.joining.JoinResponseHandler;
import mc.alk.arena.objects.teams.TeamHandler;
import io.github.TcFoxy.ArenaTOW.BattleArena.events.MyBAEvent;
import io.github.TcFoxy.ArenaTOW.BattleArena.events.MyCompetitionEvent;
import io.github.TcFoxy.ArenaTOW.BattleArena.objects.MyArenaPlayer;
import io.github.TcFoxy.ArenaTOW.BattleArena.objects.teams.MyArenaTeam;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Base class for Matches and Events
 * @author alkarin
 *
 */
public abstract class MyCompetition implements JoinResponseHandler, PlayerHolder, TeamHandler {

	/** Our teams */
	protected List<MyArenaTeam> teams = new CopyOnWriteArrayList<MyArenaTeam>();

//	/** Players that have left the match */
	protected final Set<UUID> leftPlayers = Collections.synchronizedSet(new HashSet<UUID>());

	static int count =0;

	final protected int id = count++;

	/** Our Method Controller that will handle anyone listening to this competition*/
	protected final MethodController methodController = new MethodController("id ="+id);

	/**
	 * Get the time of when the competition did the given state
	 * @return time or null if not found
	 */
	public abstract Long getTime(CompetitionState state);

	/**
	 * Get the unique ID for this competition
	 * @return id
	 */
	public int getID(){
		return id;
	}

	/**
	 * Get the Name for this competition
	 * @return Name
	 */
	public abstract String getName();

	/**
	 * Returns the current state of the competition
	 * @return CompetitionState
	 */
	@Override
    public abstract CompetitionState getState();

	/**
	 * Transition from one state to another
	 * onStart -> onVictory
	 * @param state CompetitionState
	 */
	protected abstract void transitionTo(CompetitionState state);

	/**
	 * Signify that a player has left the competition
	 * @param player ArenaPlayer
	 * @return whether the player has left or not
	 */
	public boolean playerLeft(MyArenaPlayer player) {
		return leftPlayers.contains(player.getID());
	}

	/**
	 * Returns either the MatchParams or EventParams of the match/event
	 * @return MatchParams
	 */
	@Override
    public abstract MatchParams getParams();

	/**
	 * Set our teams
	 * @param teams list of ArenaTeam
	 */
	public void setTeams(List<MyArenaTeam> teams){
		this.teams.clear();
		this.teams.addAll(teams);
		for (int i=0;i<teams.size();i++){
			teams.get(i).setIndex(i);
		}
	}

	/**
	 * return the teams for this competition
	 * @return list of ArenaTeam
	 */
	public List<MyArenaTeam> getTeams() {
		return teams;
	}

	/**
	 * Notify Bukkit Listeners and specific listeners to this match
	 * @param event BAevent
	 */
	@Override
    public void callEvent(MyBAEvent event) {
		if (event instanceof MyCompetitionEvent && ((MyCompetitionEvent)event).getCompetition()==null){
			((MyCompetitionEvent)event).setCompetition(this);}
		methodController.callEvent(event);
	}

	/**
	 * Add a collection of listeners for this competition
	 * @param transitionListeners collection of ArenaListener
	 */
	public void addArenaListeners(Collection<ArenaListener> transitionListeners){
		for (ArenaListener tl: transitionListeners){
			addArenaListener(tl);}
	}

    protected void performTransition(MatchState state, MyArenaPlayer player,
                                     MyArenaTeam team, boolean onlyInMatch){
        MyTransitionController.transition(this, state, player, team, onlyInMatch);
    }

    protected void performTransition(MatchState state, MyArenaTeam team, boolean onlyInMatch){
        MyTransitionController.transition(this, state, team, onlyInMatch);
    }

    protected void performTransition(MatchState state, Collection<MyArenaTeam> teams, boolean onlyInMatch){
        MyTransitionController.transition(this, state, teams, onlyInMatch);
    }

	/**
	 * Get the team that this player is inside of
	 * @param player ArenaPlayer
	 * @return ArenaPlayer, or null if no team contains this player
	 */
	@Override
    public MyArenaTeam getTeam(MyArenaPlayer player) {
		for (MyArenaTeam t: teams) {
			if (t.hasMember(player)) return t;}
		return null;
	}

    /**
     * Get the team that this player has left
     * @param player ArenaPlayer
     * @return ArenaPlayer, or null if no team has this player leaving
     */
    public MyArenaTeam getLeftTeam(MyArenaPlayer player) {
        for (MyArenaTeam t: teams) {
            if (t.hasLeft(player)) return t;}
        return null;
    }

	/**
	 * Get the team with this index
	 * @param teamIndex index of the team
	 * @return ArenaPlayer, or null if no team exists
	 */
	public MyArenaTeam getTeam(int teamIndex) {
		return teams.size() <= teamIndex? null : teams.get(teamIndex);
	}

	/**
	 * Is the player inside of this competition?
	 * @param player to check for
	 * @return true or false
	 */
	public boolean hasPlayer(MyArenaPlayer player) {
		for (MyArenaTeam t: teams) {
			if (t.hasMember(player)) return true;}
		return false;
	}

	/**
	 * Is the player alive and inside of this competition?
	 * @param player to check for
	 * @return true or false
	 */
	public boolean hasAlivePlayer(MyArenaPlayer player) {
		for (MyArenaTeam t: teams) {
			if (t.hasAliveMember(player)) return true;}
		return false;
	}

	/**
	 * Get the players that are currently inside of this competition
	 * @return Set of ArenaPlayers
	 */
	public Set<MyArenaPlayer> getPlayers() {
		HashSet<MyArenaPlayer> players = new HashSet<MyArenaPlayer>();
		for (MyArenaTeam t: teams){
			players.addAll(t.getPlayers());}
		return players;
	}

}
