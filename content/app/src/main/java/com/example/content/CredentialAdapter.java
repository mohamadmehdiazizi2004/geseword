package com.example.content;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CredentialAdapter extends RecyclerView.Adapter<CredentialAdapter.VH> {

    private final List<CredentialEntity> items;
    private final Context context;

    // نگه‌داری اینکه کدوم آیتم پسوردش نمایش داده شود
    private final Set<Integer> shownIds = new HashSet<>();

    public CredentialAdapter(Context context, List<CredentialEntity> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_credential, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int position) {
        CredentialEntity item = items.get(position);

        h.tvUser.setText(item.username);

        boolean isShown = shownIds.contains(item.id);
        h.tvPass.setText(isShown ? item.password : "••••••••");

        h.btnCopyUser.setOnClickListener(v -> copyToClipboard("username", item.username));
        h.btnCopyPass.setOnClickListener(v -> copyToClipboard("password", item.password));

        h.btnEye.setOnClickListener(v -> {
            if (shownIds.contains(item.id)) {
                shownIds.remove(item.id);
            } else {
                shownIds.add(item.id);
            }
            notifyItemChanged(position);
        });
    }

    private void copyToClipboard(String label, String value) {
        ClipboardManager cb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        cb.setPrimaryClip(ClipData.newPlainText(label, value));
        Toast.makeText(context, "کپی شد: " + label, Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        TextView tvUser, tvPass;
        Button btnCopyUser, btnCopyPass, btnEye;

        VH(@NonNull View itemView) {
            super(itemView);
            tvUser = itemView.findViewById(R.id.tvUser);
            tvPass = itemView.findViewById(R.id.tvPass);
            btnCopyUser = itemView.findViewById(R.id.btnCopyUser);
            btnCopyPass = itemView.findViewById(R.id.btnCopyPass);
            btnEye = itemView.findViewById(R.id.btnEye);
        }
    }
}
