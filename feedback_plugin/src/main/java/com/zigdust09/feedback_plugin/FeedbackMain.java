package com.zigdust09.feedback_plugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.net.http.*;
import java.net.URI;

public class FeedbackMain extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("Loaded Zigdust09Feedback by xWires");
    }
    @Override
    public void onDisable() {
        getLogger().info("Zigdust09Feedback has been disabled.");
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("feedback")) {
            if (args.length >= 2) {
                if (args[0].equalsIgnoreCase("bugreport")) {
                    StringBuilder descriptionBuilder = new StringBuilder(args[1]);
                    for (int arg = 2; arg < args.length; arg++) {
                        descriptionBuilder.append("+").append(args[arg]);
                    }
                    String builtDescription = descriptionBuilder.toString();
                    String username = sender.getName();
                    String url = "https://docs.google.com/forms/d/e/1FAIpQLSdAfuKFTS2f4FGKRxK5S3PmVIwsNfOEUgcWJACCNBwqQH0vRQ/formResponse?&submit=Submit?usp=pp_url&entry.1710721261={username}&entry.502369098=Bug+Report&entry.112130486={description}".replace("{username}", username).replace("{description}", builtDescription);
                    try {
                        HttpRequest request = HttpRequest.newBuilder()
                            .uri(new URI(url))
                            .GET()
                            .build();
                        HttpResponse<String> response = HttpClient.newHttpClient()
                            .send(request, HttpResponse.BodyHandlers.ofString());
                        if (response.statusCode() == 200) {
                            sender.sendMessage(ChatColor.GREEN + "Thanks for submitting a bug report!");
                        }
                        else {
                            sender.sendMessage(ChatColor.RED + "Sorry, an error occurred while submitting your bug report. Please send this error message to a server admin:");
                            sender.sendMessage("Username: " + username);
                            sender.sendMessage("Response Code: " + response.statusCode());
                            sender.sendMessage("Url: " + url);
                            sender.sendMessage("Response Body: " + response.body());
                            getServer().getLogger().severe("An error occurred while submitting a bug report. Here is info about the error:");
                            getServer().getLogger().severe("Username: " + username);
                            getServer().getLogger().severe("Response Code: " + response.statusCode());
                            getServer().getLogger().severe("Url: " + url);
                            getServer().getLogger().severe("Response Body: " + response.body());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else if (args[0].equalsIgnoreCase("suggestion")) {
                    StringBuilder descriptionBuilder = new StringBuilder(args[1]);
                    for (int arg = 2; arg < args.length; arg++) {
                        descriptionBuilder.append("+").append(args[arg]);
                    }
                    String builtDescription = descriptionBuilder.toString();
                    String username = sender.getName();

                    String url = "https://docs.google.com/forms/d/e/1FAIpQLSdAfuKFTS2f4FGKRxK5S3PmVIwsNfOEUgcWJACCNBwqQH0vRQ/formResponse?&submit=Submit?usp=pp_url&entry.1710721261={username}&entry.502369098=Suggestion&entry.112130486={description}".replace("{username}", username).replace("{description}", builtDescription);
                    try {
                        HttpRequest request = HttpRequest.newBuilder()
                            .uri(new URI(url))
                            .GET()
                            .build();
                        HttpResponse<String> response = HttpClient.newHttpClient()
                            .send(request, HttpResponse.BodyHandlers.ofString());
                        if (response.statusCode() == 200) {
                            sender.sendMessage(ChatColor.GREEN + "Thanks for submitting your suggestion!");
                        }
                        else {
                            sender.sendMessage(ChatColor.RED + "Sorry, an error occurred while submitting your suggestion. Please send this error message to a server admin:");
                            sender.sendMessage("Username: " + username);
                            sender.sendMessage("Response Code: " + response.statusCode());
                            sender.sendMessage("Url: " + url);
                            sender.sendMessage("Response Body: " + response.body());
                            getServer().getLogger().severe("An error occurred while submitting a suggestion. Here is info about the error:");
                            getServer().getLogger().severe("Username: " + username);
                            getServer().getLogger().severe("Response Code: " + response.statusCode());
                            getServer().getLogger().severe("Url: " + url);
                            getServer().getLogger().severe("Response Body: " + response.body());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else {
                    return false;
                }
            }
            else {
                return false;
            }
            return true;
        }
        return false;
    }
}