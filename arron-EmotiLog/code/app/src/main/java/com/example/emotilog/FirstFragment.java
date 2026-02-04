package com.example.emotilog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.emotilog.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private Logger emotionLogger;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.navigateSummary.setOnClickListener(v ->
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment)
        );

        emotionLogger = new Summary();

        binding.happyButton.setOnClickListener(v -> {
            handleClick("Happy");
        });

        binding.sadButton.setOnClickListener(v -> {
            handleClick("Sad");
        });

        binding.gratefulButton.setOnClickListener(v -> {
            handleClick("Grateful");
        });

        binding.angryButton.setOnClickListener(v -> {
            handleClick("Angry");
        });

        binding.excitedButton.setOnClickListener(v -> {
            handleClick("Excited");
        });

        binding.scaredButton.setOnClickListener(v -> {
            handleClick("Scared");
        });

    }

    private void handleClick(String emotion) { // Helper function for button logic
        ButtonLogic logic = new ButtonLogic(emotionLogger, emotion);
        logic.logEmotion();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}