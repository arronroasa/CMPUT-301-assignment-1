package com.example.emotilog;

import android.os.Build;
import android.os.Bundle;
import android.telephony.SubscriptionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.emotilog.databinding.FragmentSecondBinding;
import com.google.android.material.tabs.TabLayout;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        updateUI(true);

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                updateUI(tab.getPosition() == 0);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        binding.navigateMain.setOnClickListener(v ->
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment)
        );
    }

    // The following two functions are from Google, Gemini 3, "Can I have two tabs that I switch between on the summary fragment?", 2026-02-04
    private void updateUI(boolean showLogs) {
        ArrayList<LogEntry> data = Summary.getLogs();
        ArrayList<String> displayList = new ArrayList<>();

        if (showLogs) {
            // Logic for the "Logs" tab: Show every entry with its time
            for (LogEntry entry : data) {
                displayList.add(entry.getEmotion() + " - " + formatTime(entry.getTimestamp()));
            }
        } else {
            // Logic for the "Summary" tab: Count occurrences
            Map<String, Integer> stats = new HashMap<>();
            for (LogEntry entry : data) {
                stats.put(entry.getEmotion(), stats.getOrDefault(entry.getEmotion(), 0) + 1);
            }
            for (Map.Entry<String, Integer> stat : stats.entrySet()) {
                displayList.add(stat.getKey() + ": " + stat.getValue() + " times");
            }
        }

        // Apply the adapter to the ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_list_item_1, displayList);
        binding.listviewLogs.setAdapter(adapter);
    }

    private String formatTime(long epochMillis) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Instant instant = Instant.ofEpochMilli(epochMillis);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
                    .withZone(ZoneId.systemDefault());
            return formatter.format(instant);
        } else {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
            return sdf.format(new java.util.Date(epochMillis));
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}