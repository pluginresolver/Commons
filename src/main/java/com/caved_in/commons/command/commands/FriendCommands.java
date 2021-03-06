package com.caved_in.commons.command.commands;

public class FriendCommands {
//	@CommandHandler(name = "friends")
//	public void friendsCommand(Player player, String[] args) {
//		if (args.length == 0) {
//			//They issued the command '/friends' so send them a message about the help command
//			Chat.message(player, Messages.properUsage("/friends help"));
//		}
//	}
//
//	@SubCommandHandler(name = "help", parent = "friends")
//	public void friendsHelpCommand(Player player, String args[]) {
//		int page = 1;
//		//Check if the player included a page number
//		if (args.length >= 2) {
//			String pageNumber = args[1];
//			if (StringUtils.isNumeric(pageNumber)) {
//				page = Integer.parseInt(pageNumber);
//			} else {
//				//The player included a non-numeric page argument; Send an error saying so
//				Chat.message(player, Messages.invalidCommandUsage("page number"));
//			}
//		}
//		//Send the player the commands help menu
//		HelpMenus.getFriendsCommandHelpScreen().sendTo(player, page, 6);
//	}
//
//	@SubCommandHandler(name = "add", parent = "friends")
//	public void friendsAddCommand(Player player, String[] args) {
//		String playerName = player.getName();
//		if (args.length >= 2) {
//			//Get the players wrapped data
//			PlayerWrapper playerWrapper = Players.getData(playerName);
//			//Get the players friend list
//			FriendList playerFriends = playerWrapper.getFriendsList();
//			//Get the name of the person they wish to add as a friend
//			//TODO Check if they're adding themselves.
//			String addedName = args[1];
//			//Check if the player's not friends with the player they're adding
//			if (!playerFriends.isFriendsWith(addedName)) {
//				//TODO Check if the requested players ever played on a tunnels server before
//				FriendStatus requestStatus = Commons.friendDatabase.insertFriendRequest(playerName, addedName);
//				switch (requestStatus) {
//					case REQUESTED:
//						String playerAddedName = addedName;
//						//Check if the player they requested is online
//						if (Players.isOnline(addedName)) {
//							//Get the added player
//							Player addedPlayer = Players.getPlayer(addedName);
//							//Get the exact name of the player
//							playerAddedName = addedPlayer.getName();
//							//Add a new friend to the added players friend list with an un-accepted status
//							Players.getData(addedPlayer).getFriendsList().addFriend(new Friend(addedPlayer.getName(), playerName));
//							//Send the player a message saying they received a friend request
//							Chat.message(addedPlayer, Messages.friendRequestReceived(playerName));
//						}
//						//Add a friend to users friend list
//						playerFriends.addFriend(new Friend(playerName, playerAddedName, true));
//						Chat.message(player, Messages.friendRequestSent(playerAddedName));
//						break;
//					case ALREADY_FRIENDS:
//						Chat.message(player, Messages.friendAlreadyExists(addedName));
//						break;
//					case ALREADY_PENDING:
//						Chat.message(player, Messages.friendRequestAlreadyExists(addedName));
//						break;
//					default:
//						break;
//				}
//			} else {
//				Chat.message(player, Messages.friendAlreadyExists(addedName));
//			}
//		} else {
//			Chat.message(player, Messages.invalidCommandUsage("player"));
//		}
//	}
//
//	@SubCommandHandler(name = "remove", parent = "friends")
//	public void friendsRemoveCommand(Player player, String[] args) {
//		String playerName = player.getName();
//		//Get the wrapped player data
//		PlayerWrapper playerWrapper = Players.getData(playerName);
//		if (args.length > 1) {
//			FriendList friendList = playerWrapper.getFriendsList();
//			//Get the name of the person the player's removing from their friends
//			String friendName = args[1];
//			if (friendList.isFriendsWith(friendName)) {
//				Commons.friendDatabase.deleteFriendRequest(playerName, friendName);
//				if (Players.isOnline(friendName)) {
//					//Send the player a message saying they were removed from someones friends list
//					Chat.message(Players.getPlayer(friendName), Messages.friendDeletedFriend(playerName));
//					//Remove the friend object from the others players friend list
//					Players.getData(friendName).getFriendsList().removeFriend(playerName);
//				}
//				//Remove the requested name from the players friend list
//				friendList.removeFriend(friendName);
//				Chat.message(player, Messages.friendDeleted(friendName));
//			} else {
//				Chat.message(player, Messages.invalidFriendRequest(friendName));
//			}
//		} else {
//			Chat.message(player, Messages.invalidCommandUsage("name"));
//		}
//	}
//
//	public void friendsBlockCommand(Player player, String[] args) {
//		// TODO Write table in SQL for player blocks, seperate from player
//	}
//
//	@SubCommandHandler(name = "requests", parent = "friends")
//	public void friendsRequestListCommand(Player player, String[] args) {
//		String playerName = player.getName();
//		//Get all unaccepted friends of the player
//		Set<Friend> friendList = Players.getData(playerName).getFriendsList().getUnacceptedFriends();
//		int page = 1;
//		//Check if the player has friends
//		if (friendList.size() > 0) {
//			if (args.length > 1) {
//				//Parse the passed page argument
//				String pageNumber = args[1];
//				if (StringUtils.isNumeric(pageNumber)) {
//					page = Integer.parseInt(pageNumber);
//				} else {
//					Chat.message(player, Messages.invalidCommandUsage("page"));
//				}
//			}
//			//Show the friend-requests menu
//			HelpMenus.getFriendRequestsHelpScreen(friendList).sendTo(player, page, 6);
//		} else {
//			Chat.message(player, Messages.NO_PENDING_FRIENDS);
//		}
//	}
//
//	@SubCommandHandler(name = "deny", parent = "friends")
//	public void friendsDenyCommand(Player player, String[] args) {
//		String playerName = player.getName();
//		if (args.length > 1) {
//			String denyName = args[1];
//			FriendList friendList = Players.getData(player).getFriendsList();
//			if (friendList.hasRequest(denyName)) {
//				Commons.friendDatabase.deleteFriendRequest(playerName, denyName);
//				//Check if the player who's request was denied is online
//				if (Players.isOnline(denyName)) {
//					//Get their data, and send them a message saying they were rejected
//					Player deniedPlayer = Players.getPlayer(denyName);
//					Chat.message(deniedPlayer, Messages.friendDeniedRequest(playerName));
//					//Remove the request to the player denying from their friends list
//					Players.getData(deniedPlayer).getFriendsList().removeFriend(playerName);
//				}
//				friendList.removeFriend(denyName);
//				Chat.message(player, Messages.friendRequestDenied(denyName));
//			} else {
//				Chat.message(player, Messages.invalidFriendRequest(denyName));
//			}
//		} else {
//			Chat.message(player, Messages.invalidCommandUsage("name"));
//		}
//	}
//
//	@SubCommandHandler(name = "accept", parent = "friends")
//	public void friendsAcceptCommand(Player player, String[] args) {
//		//Get the players name and their wrapped data
//		String playerName = player.getName();
//		PlayerWrapper playerWrapper = Players.getData(playerName);
//		if (args.length > 1) {
//			String acceptName = args[1];
//			//Get the players friends list
//			FriendList friendList = playerWrapper.getFriendsList();
//			//Check if they have a friend request from the name they're trying to accept
//			if (friendList.hasRequest(acceptName)) {
//				Commons.friendDatabase.acceptFriendRequest(playerName, acceptName);
//				//Check if the player they're accepting is online
//				if (Players.isOnline(acceptName)) {
//					Player acceptedPlayer = Players.getPlayer(acceptName);
//					//Set the friend status to accepted and send a message
//					Players.getData(acceptedPlayer).getFriendsList().acceptFriend(playerName);
//					Chat.message(player, Messages.friendRequestAccepted(playerName));
//				}
//				//Set the friend status to accepted and send a message
//				friendList.acceptFriend(acceptName);
//				Chat.message(player, Messages.friendRequestAccept(acceptName));
//			} else {
//				Chat.message(player, Messages.invalidFriendRequest(acceptName));
//			}
//		} else {
//			Chat.message(player, Messages.invalidCommandUsage("name"));
//		}
//	}
//
//	@SubCommandHandler(name = "list", parent = "friends")
//	public void friendsListCommand(Player player, String[] args) {
//		String playerName = player.getName();
//		Set<Friend> playerFriends = Players.getData(playerName).getFriendsList().getFriends();
//		HelpScreen friendsList = HelpMenus.getFriendsListScreen(playerFriends);
//		int page = 1;
//		if (args.length >= 2) {
//			String pageArgument = args[1];
//			if (StringUtils.isNumeric(pageArgument)) {
//				page = Integer.parseInt(pageArgument);
//			} else {
//				Chat.message(player, Messages.invalidCommandUsage("page number"));
//			}
//		}
//		friendsList.sendTo(player, page, 6);
//	}
}
